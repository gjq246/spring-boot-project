package com.kpttech.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kpttech.common.pojo.DataTableResult;
import com.kpttech.common.pojo.Json;
import com.kpttech.pagepojo.User;
import com.kpttech.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/*
 * 
swagger通过注解表明该接口会生成文档，包括接口名、请求方法、参数、返回信息的等等。

@Api：修饰整个类，描述Controller的作用
@ApiOperation：描述一个类的一个方法，或者说一个接口
@ApiParam：单个参数描述
@ApiModel：用对象来接收参数
@ApiProperty：用对象接收参数时，描述对象的一个字段
@ApiResponse：HTTP响应其中1个描述
@ApiResponses：HTTP响应整体描述
@ApiIgnore：使用该注解忽略这个API
@ApiError ：发生错误返回的信息
@ApiParamImplicitL：一个请求参数
@ApiParamsImplicit 多个请求参数

http://localhost:8002/swagger-ui.html

 */

@Api(value="用户操作类",produces = "application/json")
@Controller
@RequestMapping(method = RequestMethod.POST)
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/* 获取DataTable的数据 */
	@ApiOperation(value="获取用户列表", notes="获取所有用户列表")
	@RequestMapping(value="/user/getdatatable.action")
	@ResponseBody
	public DataTableResult getDataTable(HttpServletRequest request, User user) throws Exception {
		String orderIdx = request.getParameter("order[0][column]");
		String orderProperty = request.getParameter("columns[" + orderIdx + "][name]");
		user.setOrderProperty(orderProperty);
		String orderDir = request.getParameter("order[0][dir]");
		user.setOrderDir(orderDir);

		int page = user.getStart() / user.getLength() + 1;
		user.setPage(page);
		DataTableResult result = new DataTableResult();
		result = userService.dataTableQuery(user);
		result.setDraw(user.getDraw());
		return result;
	}
	
	/* 获取单个User信息 */
	@ApiOperation(value="获取单个User信息", notes="获取单个User信息")
	@RequestMapping("/user/getsingleuser.action")
	@ResponseBody
	public Json getSingleUser(HttpServletRequest request, User user) {
		Json json = new Json();
		try {
//			String token = request.getParameter("token");
//			if (token != null && !token.isEmpty()) {
				User tempUser = new User();
				tempUser = userService.getUser(user);
				json.setMsg("获取成功");
				json.setObj(tempUser);
				json.setSuccess(true);

			//}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}

		return json;
	}
	/* 添加数据 */
	@ApiOperation(value="添加数据", notes="添加数据")
	@RequestMapping("/user/add.action")
	@ResponseBody
	public Json addData(HttpServletRequest request, User user) {
		Json j = new Json();
		try {
//			String token = request.getParameter("token");
//			if (token != null && !token.isEmpty()) {

			    User userNew = userService.saveUser(user);
				if (userNew != null) {
					j.setSuccess(true);
					j.setObj(userNew);
					j.setMsg("添加成功");
				} else {
					j.setSuccess(false);
					j.setMsg("添加失败");
				}
//			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/* 修改数据 */
	@ApiOperation(value="修改用户数据", notes="根据参数中的cid来修改用户数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "用户ID", required = true ),
            @ApiImplicitParam(name = "cusername", value = "用户名", required = true)
    })
	@RequestMapping("/user/update.action")
	@ResponseBody
	public Json updateData(HttpServletRequest request, User user) {
		Json j = new Json();
		try {
//			String token = request.getParameter("token");
//			if (token != null && !token.isEmpty()) {

				int count = userService.updateUser(user);
				if (count == 1) {
					j.setSuccess(true);
					j.setMsg("修改成功");
				} else {
					j.setSuccess(false);
					j.setMsg("修改失败");
				}
//			}

		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/* 删除数据 */
	@RequestMapping("/user/delete.action")
	@ApiOperation(value="删除数据", notes="根据参数中的ids来删除用户数据")
	@ResponseBody
	public Json deleteData(HttpServletRequest request, User user) {
		Json j = new Json();
		try {
//			String token = request.getParameter("token");
			String ids = request.getParameter("ids");
//			if (token != null && !token.isEmpty()) {
				int deletecount = userService.deleteUsers(user);
				if (deletecount > 0) {
					j.setSuccess(true);
					j.setMsg("删除完成,成功删除" + deletecount + "条");
				} else {
					j.setSuccess(false);
					j.setMsg("删除失败");
				}
//			}

		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/* 事务测试数据 */
	@RequestMapping("/user/transtest.action")
	@ResponseBody
	public Json transTest(HttpServletRequest request, User user) {
		Json j = new Json();
		try {
//			String token = request.getParameter("token");
//			if (token != null && !token.isEmpty()) {

				int count = userService.transTest();
				if (count == 1) {
					j.setSuccess(true);
					j.setMsg("事务修改成功");
				} else {
					j.setSuccess(false);
					j.setMsg("事务修改失败");
				}
//			}

		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	

}
