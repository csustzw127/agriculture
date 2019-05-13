package com.agriculture.service.impl;

import com.agriculture.dao.FarmerDao;
import com.agriculture.service.FarmerService;
import com.agriculture.vo.Farmer;
import com.agriculture.vo.User;

public class FarmerServiceImpl implements FarmerService {

	private FarmerDao farmerDao;
	
	public Farmer login(Farmer farmer) {
		// TODO Auto-generated method stub
		Farmer login_farmer = farmerDao.login(farmer);
		return login_farmer;
	}

	public boolean ajaxValidateLoginname(String username) {
		// TODO Auto-generated method stub
		//判断用户名是否已存在
	    Farmer farmer = farmerDao.findByName(username);	
	    if(farmer == null) return true;
		return false;
	}

	public void regist(Farmer farmer) {
		// TODO Auto-generated method stub
		/*
		 * 1.补齐farmer中的其他属性(表单中没有的)
		 */
		farmer.setStatus(0);
		/*
		 * 2.将farmer保存到数据库中
		 */
		farmerDao.add(farmer);
	}

	public FarmerDao getFarmerDao() {
		return farmerDao;
	}

	public void setFarmerDao(FarmerDao farmerDao) {
		this.farmerDao = farmerDao;
	}
	
}
