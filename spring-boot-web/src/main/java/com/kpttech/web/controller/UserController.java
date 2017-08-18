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
import com.kpttech.service.UserService;


@Controller
public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
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

}
