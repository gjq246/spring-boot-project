package com.kpttech.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kpttech.common.pojo.DataTableResult;
import com.kpttech.common.utils.IdsUtil;
import com.kpttech.common.utils.UUIDUtil;
import com.kpttech.mapper.TuserMapper;
import com.kpttech.pagepojo.User;
import com.kpttech.pojo.Tuser;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


/**
 * 注意service层主要接收pagepojo的模型参数，特别的情况可以自己声明，返回值一般是list或者pagepojo或者整型
 * @author GJQ
 *
 */

@Service
public class UserService {
	
	private static final Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	private TuserMapper tuserMapper;
	
	/* 分页查询 */
	public DataTableResult dataTableQuery(User user) {
		
		PageHelper.startPage(user.getPage(), user.getLength());
		List<User> listUser = tuserMapper.getUserList(user);
		PageInfo<User> pageinfo = new PageInfo<User>(listUser);
		DataTableResult result = new DataTableResult();
		result.setRecordsTotal(pageinfo.getTotal());
		result.setRecordsFiltered(result.getRecordsTotal());
		result.setData(pageinfo.getList());

		return result;
	}
	
	/* 跟据cid删除数据 根据in删除 */
	public int deleteUsers(User user) {
		Example example = new Example(Tuser.class);
		Criteria criteria = example.createCriteria();
		int count = 0;
		if (StringUtils.isNotEmpty(user.getIds())) {

			List<Object> list = IdsUtil.getListData(user.getIds());
			if (list != null && list.size() > 0) {

				criteria.andIn("cid", list);
				count =tuserMapper.deleteByExample(example);
			}
		}
		return count;
	}
	
	/* 获得单个用户的信息 by用户id */
	public User getUser(User user) {
		User resultUser=new User();
		if (StringUtils.isNotEmpty(user.getCid())) {
			Tuser tuser = new Tuser();
			tuser.setCid(user.getCid());
			Tuser tempTuser = tuserMapper.selectOne(tuser);
			if (tempTuser != null) {
				try {
					BeanUtils.copyProperties(resultUser, tempTuser);
//					resulttuser.setUserPwd("");
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
		return resultUser;
	}
	
	/* 更新用户信息  */
	public int updateUser(User user) {
		int count =0;
		if (StringUtils.isNotEmpty(user.getCid())) // 用户ID不能为空
		{
			Tuser tuser = new Tuser();
			
			try {
				BeanUtils.copyProperties(tuser, user);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count = tuserMapper.updateByPrimaryKeySelective(tuser);
		}
		return count;
	}

	/* 保存用户 */
	public User saveUser(User user) {
		User resultUser=new User();
		Tuser tuser = new Tuser();
		try {
			BeanUtils.copyProperties(tuser, user);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String cid = UUIDUtil.gernerateUUID();
		tuser.setCid(cid);
		Timestamp ts = new Timestamp((new Date()).getTime());
		tuser.setTuseraddtime(ts);
		int count  = tuserMapper.insertSelective(tuser);
		if(count>0){
			try {
				BeanUtils.copyProperties(resultUser, tuser);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resultUser;
		
	}
	
	/*事务测试
	 * 解决方案： 
  方案1.例如service层处理事务，那么service中的方法中不做异常捕获，或者在catch语句中最后增加throw new RuntimeException()语句，
  以便让aop捕获异常再去回滚，并且在service上层（webservice客户端，view层action）要继续捕获这个异常并处理
  方案2.在service层方法的catch语句中增加：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
  语句，手动回滚，这样上层就无需去处理异常（现在项目的做法）
	 * 
	 * */
	@Transactional
	public int transTest(){
		int resultCode=0;
		try{
			Tuser tuser = new Tuser();
			tuser.setCid("1");
			tuser.setCusername("1修改");
			int count = tuserMapper.updateByPrimaryKeySelective(tuser);
			logger.info("1:"+count);
			int i=1/0;
			tuser.setCid("1");
			tuser.setCusername("2修改");
			count = tuserMapper.updateByPrimaryKeySelective(tuser);
			logger.info("2:"+count);
			resultCode=1;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("err:");
			throw new RuntimeException();
		}		
		return resultCode;
	}

}
