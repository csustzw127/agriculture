package com.agriculture.dao;

import com.agriculture.vo.Farmer;

public interface FarmerDao {

	Farmer findByName(String username);

	void add(Farmer farmer);

	Farmer login(Farmer farmer);

}
