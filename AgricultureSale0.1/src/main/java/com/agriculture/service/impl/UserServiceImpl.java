package com.agriculture.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import com.agriculture.dao.UserDao;
import com.agriculture.service.UserService;
import com.agriculture.vo.User;
import com.utils.CommonUtils;
import com.utils.Mail;
import com.utils.MailUtils;


public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	public boolean active(String activecode) {
		// TODO Auto-generated method stub
		User user = userDao.findByActiveCode(activecode);
		if(user.getStatus() == 1) return false;
		user.setStatus(1);
		userDao.updateUser(user);
		return true;
	}
	
	public User login(User user) {
		// TODO Auto-generated method stub
		User login_user;
		login_user = userDao.login(user);
		return login_user;
		
	}
	
	public boolean ajaxValidateLoginname(String username) {
		// TODO Auto-generated method stub
		//判断用户名是否已存在
	    User user = userDao.findByName(username);	
	    if(user == null) return true;
		return false;
	}
	
	public boolean ajaxValidateEmail(String email) {
		// TODO Auto-generated method stub
		//判断邮箱是否已存在
	    User user = userDao.findByEmail(email);	
	    if(user == null) return true;
		return false;
	}

	public void regist(User user) {
		/*
		 * 1.补齐user中的其他属性(表单中没有的)
		 */
		user.setActivecode(CommonUtils.uuid() + CommonUtils.uuid());
		user.setStatus(0);
		/*
		 * 2.将user保存到数据库中
		 */
		userDao.add(user);
		
		/*
		 * 3.发送邮件激活
		 */
		
		//获取配置文件中的配置
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		 /*
		  * 登陆邮件服务器，获取session
		  */
		String host = prop.getProperty("host");
		String name = prop.getProperty("username");
		String pass = prop.getProperty("password");
	    Session session = MailUtils.createSession(host, name, pass);
	    
	    /*
	     * 创建邮件对象
	     */
	    String from = prop.getProperty("from");
	    String to = user.getEmail();
	    String subject = prop.getProperty("subject");
	    String content = prop.getProperty("content");
	    content = MessageFormat.format(content, user.getActivecode());
	    
	    Mail mail = new Mail(from, to, subject, content);
	    try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	    
	}
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	
	
	
}