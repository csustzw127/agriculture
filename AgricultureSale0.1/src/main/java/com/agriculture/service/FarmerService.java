package com.agriculture.service;

import com.agriculture.vo.Farmer;

public interface FarmerService {

	boolean ajaxValidateLoginname(String username);

	void regist(Farmer farmer);

	Farmer login(Farmer farmer);

}
