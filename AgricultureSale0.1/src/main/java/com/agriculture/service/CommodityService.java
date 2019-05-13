package com.agriculture.service;

import com.agriculture.vo.Commodity;
import com.agriculture.vo.Farmer;
import com.agriculture.vo.Page;

public interface CommodityService {

	void add(Commodity comm);

	Commodity findByCommId(int id);

	Page<Commodity> findByFarmer(Farmer farmer, int currPage);

	Page<Commodity> findByCategory(int currPage, int cate_id);

	Page<Commodity> findByName(String condition, int currPage);
	
}
