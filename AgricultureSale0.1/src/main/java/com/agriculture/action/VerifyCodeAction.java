package com.agriculture.action;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.utils.VerifyCode;

public class VerifyCodeAction {
	
	private String verifycode;
   
	public String VerifyCode(){
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();//获取一次性验证码图片
		// 该方法必须在getImage()方法之后来调用
//		System.out.println(vc.getText());//获取图片上的文本
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
    	response.setCharacterEncoding("utf-8");
		try {
			VerifyCode.output(image, response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//把图片写到指定流中
		
		// 把文本保存到session中，为LoginServlet验证做准备
		request.getSession().setAttribute("vCode", vc.getText());
		return Action.NONE;
	}
	
	public String ajaxValidateVerifyCode()  {
		String msg;
		boolean b; // 校验
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
    	response.setCharacterEncoding("utf-8");
        String vCode = (String) request.getSession().getAttribute("vCode");
        if(vCode.equalsIgnoreCase(verifycode)) b = true;
        else b = false;
    	if(b == true) {
    		msg = "{\"msg\":\"验证码正确\"}";
    	} else {
    		msg = "{\"msg\":\"验证码不正确\"}";
    	}
    	try {
			response.getWriter().println(msg);// 将结果发送回客户端
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return Action.NONE;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	
}
