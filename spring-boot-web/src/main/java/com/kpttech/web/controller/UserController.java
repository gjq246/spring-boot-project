package com.kpttech.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kpttech.common.pojo.DataTableResult;
import com.kpttech.common.pojo.Json;
import com.kpttech.pagepojo.User;
import com.kpttech.service.RedisService;
import com.kpttech.service.UserService;


@Controller
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@Autowired
	private RedisService redisService;
	
	/* 获取DataTable的数据 */
	@RequestMapping("/user/getdatatable.action")
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
	
	/* redis测试 */
	@RequestMapping("/user/redistest.action")
	@ResponseBody
	public Json RedisTest(HttpServletRequest request, User user) {
		Json j = new Json();
		try {
			// String token = request.getParameter("token");
			// if (token != null && !token.isEmpty()) {

			redisService.setKey("name", "john",10);

			j.setSuccess(true);
			j.setObj(redisService.getValue("name"));
			j.setMsg("成功");

			// }
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}

}
