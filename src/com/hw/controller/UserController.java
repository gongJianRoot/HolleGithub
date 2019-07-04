package com.hw.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hw.entity.User;
import com.hw.service.UserService;

/**
 * 用户的控制器
 * 
 * @author Smile
 *
 */
@SuppressWarnings("unused")
@Controller
public class UserController {

	// 依赖注入
	UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户登陆控制
	 * 
	 * @param user
	 * @return String
	 */
	@RequestMapping("/login")
	public String login(User user, HttpSession session) {
		// 登陆内容空白控制
		if ("".equals(user.getUserName()) && "".equals(user.getUserPwd())) {
			return "redirect:register.jsp";
		} else if ("".equals(user.getUserPwd())) {
			return "redirect:register.jsp";
		} else if ("".equals(user.getUserName())) {
			return "redirect:register.jsp";
		}
		// 具体登陆的人并登陆
		User oneUser = new User(user.getUserName(), user.getUserPwd());
		User user_ = userService.loginByNameAndPwd(oneUser);
		// 根据登陆内容判断
		if (user_ == null) {
			return "redirect:register.jsp";
		} else if (user_.getUserType() == 0) {
			userService.selectAllUserInfo();
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userId", user_.getUserId());
			return "redirect:getAllDramas.action";
		} else if (user_.getUserType() == 1) {
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userId", user_.getUserId());
			return "redirect:getAllDramas_User.action";
		}
		return "redirect:register.jsp";
	}

	/**
	 * 用户注册控制
	 * 
	 * @param user
	 * @return String
	 */
	@RequestMapping("/register")
	public String register(User user) {
		// 注册内容空白控制
		if ("".equals(user.getUserName()) && "".equals(user.getUserPwd())) {
			return "redirect:register.jsp";
		} else if ("".equals(user.getUserPwd())) {
			return "redirect:register.jsp";
		} else if ("".equals(user.getUserName())) {
			return "redirect:register.jsp";
		}
		// 先判断注册信息是否已经存在
		User oneUser = new User(user.getUserName(), user.getUserPwd());
		User user__ = userService.loginByNameAndPwd(oneUser);
		// 如果不存在，进行注册
		if (user__ == null) {
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", user.getUserName());
			map.put("userPwd", user.getUserPwd());
			map.put("registerTime", timeStamp);
			int num = userService.registerByNameAndPwd(map);
			if (num == 1) {
				return "redirect:Login.jsp";
			} else if (num != 1) {
				return "redirect:register.jsp";
			}
			// 如果存在直接登陆
		} else {
			return "redirect:Login.jsp";
		}
		return "redirect:register.jsp";
	}

	/**
	 * 用户注册控制用户名唯一性
	 * 
	 * @ResponseBody 将请求的字符串，映射成一个java对象，字符串的key自动映射对象的属性
	 * @RequestBody 将响应的对象，转换成json对象
	 * @param userName
	 * @return User
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("/registerByName")
	public @ResponseBody User registerByName(String userName) throws ServletException, IOException {
		User user = userService.registerByName(userName);
		System.out.println(user);
		// 根据判断调用注册还是登陆的方法
		registerOrLogin(user);
		return user;
	}

	/**
	 * 通过注册验证的情况判断是否继续注册，还是进入到登录页面
	 * 
	 * @param user
	 * @return String
	 * @throws IOException
	 * @throws ServletException
	 */
	public String registerOrLogin(User user) {
		if (user != null) {

		} else if (user == null) {
		}
		return "redirect:register.jsp";
	}

}
