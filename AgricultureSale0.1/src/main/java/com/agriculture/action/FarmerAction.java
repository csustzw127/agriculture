package com.agriculture.action;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.agriculture.service.FarmerService;
import com.agriculture.vo.Farmer;
import com.opensymphony.xwork2.Action;

public class FarmerAction {
	private String username;
	private String psw;
	private String email;
	private String nickname;
	private String verifycode;
	private String phone;
	private FarmerService farmerService;
	
	public String ajaxValidateUsername()  {
		String msg;
		boolean b = farmerService.ajaxValidateLoginname(username); // 校验
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
		Farmer farmer = new Farmer();
		farmer.setUsername(username);
		farmer.setEmail(email);
		farmer.setPsw(psw);
		farmerService.regist(farmer);
		
		//3、向信息页面添加信息
		request.setAttribute("code", "success");
		request.setAttribute("title","完成");
		request.setAttribute("msg", "注册成功，请等待管理员审核");
    	
		return Action.SUCCESS;
	}
	/*
	 * 登錄
	 */
	public String login() {
		HashMap<String,String> errors = new HashMap<String,String>();
		HashMap<String,String> form = new HashMap<String,String>();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		validateLogin(errors);
		//封装数据
		Farmer farmer  = new Farmer();
		farmer.setUsername(username);
		farmer.setPsw(psw);
		//1 校验数据是否合法
		if(errors.size()>0) {
			form.put("username", username);
			form.put("psw", psw);
			request.setAttribute("errors", errors);
			return Action.ERROR;
		}
		//2 判断登录账号密码是否匹配
		Farmer login_farmer = farmerService.login(farmer);
		if(login_farmer == null) {
			errors.put("username", "用户名与密码不匹配");
			request.setAttribute("errors", errors);
			return Action.ERROR;
		}
		//3 将user信息存入session中
		session.setAttribute("farmer", login_farmer);
		return Action.SUCCESS;
	}
	/*
	 * 校验注册的数据
	 */
	private void validate(HashMap<String,String> errors) {
		//1.校验用户名
		if (username == null || username.isEmpty()) {
			errors.put("username", "用户名不能为空！");
		} else if(username.length()<3||username.length()>20) {
			errors.put("username", "用户名长度必须在3~20之间！");
		} else if(!farmerService.ajaxValidateLoginname(username)) {
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
	 *校验登录的数据 
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getVerifycode() {
		return verifycode;
	}
	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	public FarmerService getFarmerService() {
		return farmerService;
	}
	public void setFarmerService(FarmerService farmerService) {
		this.farmerService = farmerService;
	}
	
	
}
