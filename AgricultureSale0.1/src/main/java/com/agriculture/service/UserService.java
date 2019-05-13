package com.agriculture.service;

import com.agriculture.vo.User;

public interface UserService {

	boolean ajaxValidateLoginname(String username);
	
	boolean ajaxValidateEmail(String email);

	void regist(User user);

	boolean active(String activecode);

	User login(User user);
}
