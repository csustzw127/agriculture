package com.agriculture.dao;

import com.agriculture.vo.Commodity;
import com.agriculture.vo.Farmer;
import com.agriculture.vo.Page;

public interface CommodityDao {

	void add(Commodity comm);

	Page<Commodity> findByFarmer(Farmer farmer, int start,int num);

	Page<Commodity> findByCategory(int i, int num, int cate_id);

	Page<Commodity> findByName(String name, int i, int num);

	Commodity findByCommId(int id);

}
