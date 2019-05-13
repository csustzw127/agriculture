package com.agriculture.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.agriculture.service.UserService;
import com.agriculture.vo.User;
import com.opensymphony.xwork2.Action;
import com.utils.CommonUtils;

public class UserAction {
	private String username;
	private String psw;
	private String email;
	private String nickname;
	private String verifycode;
	private UserService userService;
    private String activecode;	

	public String ajaxValidateUsername()  {
		String msg;
		boolean b = userService.ajaxValidateLoginname(username); // 校验
		HttpServletResponse response = ServletActionContext.getResponse();
    	response.setCharacterEncoding("utf-8");
    	if(b == true) {
    		msg = "{\"msg\":\"用户名可用\"}";
    	} else {
    		msg = "{\"msg\":\"用户名已存在\"}";
    	}
    	try {
			response.getWriter().println(msg);// 将结果发送回客户端
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return Action.NONE;
	}
	/**
	 * 登录
	 */
	public String login() {
		HashMap<String,String> errors = new HashMap<String,String>();
		HashMap<String,String> form = new HashMap<String,String>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		validateLogin(errors);
		//封装数据
		User user  = new User();
		user.setUsername(username);
		user.setPsw(psw);
		//1 校验数据是否合法
		if(errors.size()>0) {
			form.put("username", username);
			form.put("psw", psw);
			request.setAttribute("errors", errors);
			return Action.ERROR;
		}
		//2 判断登录账号密码是否匹配
		User login_user = userService.login(user);
		if(login_user == null) {
			errors.put("username", "用户名与密码不匹配");
			request.setAttribute("errors", errors);
			return Action.ERROR;
		}
		//3 将user信息存入session中
		session.setAttribute("user", login_user);
		return Action.SUCCESS;
	}
     /*
      * 校验登录数据是否合法
      */
	private void validateLogin(HashMap<String,String> errors ) {
		// TODO Auto-generated method stub
		//1.校验用户名
		if (username == null || username.isEmpty()) {
			errors.put("username", "用户名不能为空！");
		} else if(username.length()<3||username.length()>20) {
			errors.put("username", "用户名长度必须在3~20之间！");
		}		
		//2.校验密码
		if (psw == null || psw.isEmpty()) {
			errors.put("psw", "密码不能为空！");
		} else if(psw.length()<3||psw.length()>20) {
			errors.put("psw", "密码长度必须在3~20之间！");
		}
		//3.校验验证码
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if (verifycode == null || verifycode.isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if(!verifycode.equalsIgnoreCase((String)session.getAttribute("vCode"))) {
			errors.put("verifyCode", "验证码错误！");
		}
	}
	/**
	 * 校验邮箱是否已注册
	 * @return
	 */
	public String ajaxValidateEmail()  {
		String msg;
		boolean b = userService.ajaxValidateEmail(email); // 校验
		HttpServletResponse response = ServletActionContext.getResponse();
    	response.setCharacterEncoding("utf-8");
    	if(b == true) {
    		msg = "{\"msg\":\"邮箱可用\"}";
    	} else {
    		msg = "{\"msg\":\"邮箱已存在\"}";
    	}
    	try {
			response.getWriter().println(msg);// 将结果发送回客户端
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return Action.NONE;
	}
	/**
	 *  注册
	 * @return 注册成功返回称成功页面，失败返回注册页面，显示失败原因
	 */
	public String regist() {
		HashMap<String,String> errors = new HashMap<String,String>();
		HttpServletRequest request = ServletActionContext.getRequest();
		//1、校验数据是否合法
		validate(errors);
		if(errors.size()>0) {
			request.setAttribute("errors", errors);
			return Action.ERROR;
		}
		
		//2、向数据库添加信息
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPsw(psw);
		userService.regist(user);
		
		//3、向信息页面添加信息
		request.setAttribute("code", "success");
		request.setAttribute("title","完成");
		request.setAttribute("msg", "注册成功，请前往注册邮箱进行帐号激活");
    	
		return Action.SUCCESS;
	}
	
	private void validate(HashMap<String,String> errors) {
		//1.校验用户名
		if (username == null || username.isEmpty()) {
			errors.put("username", "用户名不能为空！");
		} else if(username.length()<3||username.length()>20) {
			errors.put("username", "用户名长度必须在3~20之间！");
		} else if(!userService.ajaxValidateLoginname(username)) {
			errors.put("username","用户名已注册！");
		}
		
		//2.校验密码
		if (psw == null || psw.isEmpty()) {
			errors.put("psw", "密码不能为空！");
		} else if(psw.length()<3||psw.length()>20) {
			errors.put("psw", "密码长度必须在3~20之间！");
		} 
		
		//4.校验邮箱
		if (email == null || email.isEmpty()) {
			errors.put("email", "邮箱不能为空！");
		} else if(!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
			errors.put("email", "邮箱格式不正确！");
		} else if(!userService.ajaxValidateEmail(email)) {
			errors.put("email", "邮箱已被注册!");
		}
		
		
		//5.校验验证码
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if (verifycode == null || verifycode.isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if(!verifycode.equalsIgnoreCase((String)session.getAttribute("vCode"))) {
			errors.put("verifyCode", "验证码错误！");
		}
	}
	/*
	 * 激活注册账号
	 */
	public String active(){
		
		boolean res;
		res = userService.active(activecode);
		if(res == false) {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("code", "error");
			request.setAttribute("title","激活无效");
			request.setAttribute("msg", "请勿重复激活");
			return Action.ERROR;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("code", "success");
		request.setAttribute("title","激活完成");
		request.setAttribute("msg", "激活成功，请前往主页进行购物，祝您有个愉快的购物时光");
    	
		return Action.SUCCESS;
	}
	
	public String getActivecode() {
		return activecode;
	}

	public void setActivecode(String activecode) {
		this.activecode = activecode;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
