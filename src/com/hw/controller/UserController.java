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
 * �û��Ŀ�����
 * 
 * @author Smile
 *
 */
@SuppressWarnings("unused")
@Controller
public class UserController {

	// ����ע��
	UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * �û���½����
	 * 
	 * @param user
	 * @return String
	 */
	@RequestMapping("/login")
	public String login(User user, HttpSession session) {
		// ��½���ݿհ׿���
		if ("".equals(user.getUserName()) && "".equals(user.getUserPwd())) {
			return "redirect:register.jsp";
		} else if ("".equals(user.getUserPwd())) {
			return "redirect:register.jsp";
		} else if ("".equals(user.getUserName())) {
			return "redirect:register.jsp";
		}
		// �����½���˲���½
		User oneUser = new User(user.getUserName(), user.getUserPwd());
		User user_ = userService.loginByNameAndPwd(oneUser);
		// ���ݵ�½�����ж�
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
	 * �û�ע�����
	 * 
	 * @param user
	 * @return String
	 */
	@RequestMapping("/register")
	public String register(User user) {
		// ע�����ݿհ׿���
		if ("".equals(user.getUserName()) && "".equals(user.getUserPwd())) {
			return "redirect:register.jsp";
		} else if ("".equals(user.getUserPwd())) {
			return "redirect:register.jsp";
		} else if ("".equals(user.getUserName())) {
			return "redirect:register.jsp";
		}
		// ���ж�ע����Ϣ�Ƿ��Ѿ�����
		User oneUser = new User(user.getUserName(), user.getUserPwd());
		User user__ = userService.loginByNameAndPwd(oneUser);
		// ��������ڣ�����ע��
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
			// �������ֱ�ӵ�½
		} else {
			return "redirect:Login.jsp";
		}
		return "redirect:register.jsp";
	}

	/**
	 * �û�ע������û���Ψһ��
	 * 
	 * @ResponseBody ��������ַ�����ӳ���һ��java�����ַ�����key�Զ�ӳ����������
	 * @RequestBody ����Ӧ�Ķ���ת����json����
	 * @param userName
	 * @return User
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("/registerByName")
	public @ResponseBody User registerByName(String userName) throws ServletException, IOException {
		User user = userService.registerByName(userName);
		System.out.println(user);
		// �����жϵ���ע�ỹ�ǵ�½�ķ���
		registerOrLogin(user);
		return user;
	}

	/**
	 * ͨ��ע����֤������ж��Ƿ����ע�ᣬ���ǽ��뵽��¼ҳ��
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
