package com.kpttech.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kpttech.common.pojo.Json;
import com.kpttech.common.utils.HttpClientUtils;
import com.kpttech.common.utils.Sha1Util;
import com.kpttech.pagepojo.WxAccessToken;
import com.kpttech.pagepojo.WxUserInfo;
import com.kpttech.pagepojo.WxWechatTemplate;
import com.kpttech.service.RedisService;
import com.kpttech.web.WxConfig;


/**
 * *@author GJQ 10:08 2017/8/30
 */

@Controller
public class WeixinController {

	private static final Logger logger = Logger.getLogger(WeixinController.class);

    private int SSO_SESSION_EXPIRE = 120;// 超时时间7200秒=120分钟，微信的时间
	@Autowired
	private RedisService jedisClient;

	@Autowired
	private WxConfig unionCofig;
	
	private final String SEND_TEMPLAYE_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	@RequestMapping("/wx/api.action")
	public void signature(@RequestParam(value = "signature", required = true) String signature,
			@RequestParam(value = "timestamp", required = true) String timestamp,
			@RequestParam(value = "nonce", required = true) String nonce,
			@RequestParam(value = "echostr", required = true) String echostr, HttpServletResponse response)
			throws IOException {
		String[] values = { "fzkpt", timestamp, nonce };
		Arrays.sort(values); // 字典序排序
		String value = values[0] + values[1] + values[2];
		String sign = DigestUtils.shaHex(value);
		PrintWriter writer = response.getWriter();
		if (signature.equals(sign)) {// 验证成功返回ehcostr
			writer.print(echostr);
		} else {
			writer.print("error");
		}
		writer.flush();
		writer.close();
	}

	@RequestMapping("/wx/auth.action")
	@ResponseBody
	public void auth(HttpServletRequest request,HttpServletResponse resp) {
		Json json = new Json();
		Json resultJson=new Json();
		WxUserInfo wxUserInfo =new WxUserInfo();
		WxAccessToken wxAccessToken=new WxAccessToken();
		try {

			if (StringUtils.isNotEmpty(request.getParameter("code"))) {
				// 获取code后，请求以下链接获取access_token：
				// https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
				String code = request.getParameter("code");
				String result = HttpClientUtils.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + unionCofig.getAppID()
						+ "&secret=" + unionCofig.getAppsecret() + "&code=" + code + "&grant_type=authorization_code");
				logger.info(result);
				wxAccessToken = JSON.parseObject(result, WxAccessToken.class);
				// https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
				result = HttpClientUtils.doGet("https://api.weixin.qq.com/sns/userinfo?access_token="
						+ wxAccessToken.getAccess_token() + "&openid=" + wxAccessToken.getOpenid() + "&lang=zh_CN");
				wxUserInfo = JSON.parseObject(result, WxUserInfo.class);

				// 检查openid是否已注册，否则加入数据库
				logger.info(wxAccessToken.getOpenid());
				logger.info(wxUserInfo.getNickname());
 
				json.setSuccess(true);
				json.setObj(wxAccessToken.getOpenid());
			/*	TUser tuser=new TUser();
				tuser.setUserWxcity(wxUserInfo.getCity());
				tuser.setUserWxcountry(wxUserInfo.getCountry());
				tuser.setUserWxnickname(wxUserInfo.getNickname());
				tuser.setUserWxopenid(wxUserInfo.getOpenid());
				tuser.setUserWxprivilege(wxUserInfo.getPrivilege().toString());
				tuser.setUserWxprovince(wxUserInfo.getProvince());
				tuser.setUserWxunionid(wxUserInfo.getUnionid());
				tuser.setUserName(wxUserInfo.getNickname());
				if(wxUserInfo.getSex().equals("0"))
				{
					tuser.setUserSex("");
				}
				else if(wxUserInfo.getSex().equals("1"))
				{
					tuser.setUserSex("男");
				}
				else
				{
					tuser.setUserSex("女");
				}
				
				tuser.setUserPhoto(wxUserInfo.getHeadimgurl());
				resultJson=userService.saveUser(tuser);*/
				/*
				 * {"success":true,"msg":"用户注册成功!","code":200,"obj":{"openid":
				 * "oW4lCv0WH9GR7LXrVKYM6EaKpmLQ","nickname":"清","sex":"1",
				 * "province":"福建","city":"福州","country":"中国","headimgurl":
				 * "http://wx.qlogo.cn/mmopen/xkzUDNH9dQE6LEH6WHRNYxf7s8ymg07I0EibxrV9wtIUlckalwkJ51GF8hXXQhwLhJgxrL3AN9ibMIVy9SEIJl1en7IQ69tURy/0"
				 * ,"privilege":[],"unionid":null}}
				 */
				json.setMsg("用户授权成功!");
			} else {
				json.setSuccess(false);
				json.setMsg("用户禁止授权!");
			}
			
			resp.sendRedirect(unionCofig.getReturnUrl()+"?openid="+json.getObj());//加上根目录地址，跳回个人页面
			

		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
//		return json;
		
//		return "test.htm";

		
	}

	// 支付
	// 1.参考以下文档获取access_token（有效期7200秒，开发者必须在自己的服务全局缓存access_token）：../15/54ce45d8d30b6bf6758f68d2e95bc627.html
	// 2.用第一步拿到的access_token 采用http
	// GET方式请求获得jsapi_ticket（有效期7200秒，开发者必须在自己的服务全局缓存jsapi_ticket）：https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
    //考虑存入reids，加入过期控制
	private String getAccessToken() {
		// String appid="你公众号基本设置里的应用id";//应用ID
		// String appSecret="你公众号基本设置里的应用密钥";//(应用密钥)
		String accessToken = "";
		try {
			// 先尝试从缓存取
			accessToken = jedisClient.getValue("AccessTokenKey:");
			if (StringUtils.isEmpty(accessToken)) {
				// 没有缓存，重新获取

				String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + unionCofig.getAppID()
						+ "&secret=" + unionCofig.getAppsecret() + "";
				String backData = HttpClientUtils.doGet(url);

				JSONObject j = JSONObject.parseObject(backData);
				accessToken = j.getString("access_token");

				jedisClient.setKey("AccessTokenKey:", accessToken, SSO_SESSION_EXPIRE);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	private String getJSApiTicket() {
		String ticket = "";
		try {
			// 先尝试从缓存取
			ticket = jedisClient.getValue("JSApiTicketKey:");
			if (StringUtils.isEmpty(ticket)) {
				// 获取token
				String acess_token = getAccessToken();
				
				if(StringUtils.isEmpty(acess_token)){
					return ticket;
				}

				String urlStr = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + acess_token
						+ "&type=jsapi";
				String backData = HttpClientUtils.doGet(urlStr);

				JSONObject j = JSONObject.parseObject(backData);
				ticket = j.getString("ticket");
				
				jedisClient.setKey("JSApiTicketKey:", ticket,SSO_SESSION_EXPIRE);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}
	/**
	 * 消息模板发送方法
	 * @param accessToken
	 * @param wechatTemplate
	 * @return
	 */
	private boolean sendTemplateMessage(String accessToken, WxWechatTemplate wechatTemplate) {
		boolean flag = false;
		try {
			
			String jsonString = JSON.toJSONStringWithDateFormat(wechatTemplate, "yyyy-MM-dd HH:mm:ss");
			String requestUrl = SEND_TEMPLAYE_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);
			String result = HttpClientUtils.doPostJson(requestUrl, jsonString);
			if (StringUtils.isNotEmpty(result)) {
				JSONObject jsonObject = JSON.parseObject(result);
				int errorCode = jsonObject.getInteger("errcode");
				if (0 == errorCode) {
					logger.info("模板消息发送成功");
					flag = true;
				} else {
					String errorMsg = jsonObject.getString("errmsg");
					logger.info("模板消息发送失败,错误是 " + errorCode + ",错误信息是" + errorMsg);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@RequestMapping("/wx/sendMessage.action")
	@ResponseBody
	public Json sendMessage(HttpServletRequest request,HttpServletResponse resp)
	{
		Json j=new Json();
		logger.info("--------0-------");
		String accessToken = getAccessToken();
		logger.info("--------1-------");
		logger.info(accessToken);
		if(StringUtils.isEmpty(accessToken))
		{
			j.setMsg("accessToken为空！");
			return j;
		}
		try
		{
			logger.info("------2---------");
			String wechatTemplateStr=request.getParameter("wechatTemplateStr");
			logger.info("------3---------");
			logger.info(wechatTemplateStr);
			List<WxWechatTemplate> listWxWechatTemplate=JSON.parseArray(wechatTemplateStr, WxWechatTemplate.class);
			logger.info("------4---------");
			logger.info(listWxWechatTemplate.size());
			if(listWxWechatTemplate!=null&&listWxWechatTemplate.size()>0)
			{
				logger.info("------5---------");
				boolean flag=true;
				int count=0;
				for(WxWechatTemplate tempWxWechatTemplate:listWxWechatTemplate)
				{
					flag=sendTemplateMessage(accessToken, tempWxWechatTemplate);
					if(flag)
					{
						count++;
					}
				}
				j.setSuccess(true);
				j.setMsg("发送消息成功");
				j.setObj(count);
			}
		}
		catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * 支付配置
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/wx/config.action")
	@ResponseBody
	public Json config(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Json json = new Json();

		String jsapi_ticket = getJSApiTicket();// 看清楚.这是ticket..用token在https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi里换的
		
		logger.info("jsapi_ticket:"+jsapi_ticket);
		
		String nonce_str = Sha1Util.getNonceStr();// 随机字符串
		String timestamp = Sha1Util.getTimeStamp();// 时间戳
		// String appid = "";//APPID,谁在问我为什么报没有APPID就去死吧
		String url = "";// 发起支付的前端页面的URL地址.而且...而且必须在微信支付里面配置才行!!!
		String sign = null;
		try {
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("jsapi_ticket", jsapi_ticket);
			packageParams.put("noncestr", nonce_str);
			packageParams.put("timestamp", timestamp);
			packageParams.put("url", url);
			sign = Sha1Util.createSHA1Sign(packageParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String res="appId : \"" + appid + "\",timestamp : \"" + timestamp
		// //微信个傻逼..这里的timestamp是小写~~
		// + "\", nonceStr : \"" + nonce_str
		// + "\", signature : \"" + sign + "\"";

		SortedMap<String, String> retMsgJson = new TreeMap<String, String>();
		// retMsgJson.put("debug", "true");
		retMsgJson.put("appId", unionCofig.getAppID());
		retMsgJson.put("timestamp", timestamp);
		retMsgJson.put("noncestr", nonce_str);
		retMsgJson.put("signature", sign);

		// List<String> jsApiList=new ArrayList<String>();
		// jsApiList.add("chooseWXPay");
		// retMsgJson.put("jsApiList", jsApiList);

		json.setSuccess(true);
		json.setObj(retMsgJson);

		return json;// 返回config配置信息
	}
	
}