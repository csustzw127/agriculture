package com.agriculture.service.impl;

import com.agriculture.dao.CommodityDao;
import com.agriculture.service.CommodityService;
import com.agriculture.vo.Commodity;
import com.agriculture.vo.Farmer;
import com.agriculture.vo.Page;
import com.agriculture.vo.PageConstant;

public class CommodityServiceImpl implements CommodityService{

	CommodityDao commodityDao;
	public void add(Commodity comm) {
		// TODO Auto-generated method stub
		commodityDao.add(comm);
	}
	
	public Page<Commodity> findByCategory(int currPage,int cate_id) {
		// TODO Auto-generated method stub
		int num = PageConstant.EVERY_COMMODITYPAGE_NUM;
		Page<Commodity> page = commodityDao.findByCategory((currPage-1)*num,num,cate_id);
		page.setUrl("commodity/findByCategory.action?cate_id="+cate_id);
		return page;
	}
	
	public Page<Commodity> findByName(String name,int currPage) {
		// TODO Auto-generated method stub
		int num = PageConstant.EVERY_COMMODITYPAGE_NUM;
		Page<Commodity> page = commodityDao.findByName(name,(currPage-1)*num,num);
		page.setUrl("commodity/findByName.action?name="+name);
		return page;
	}
	public Page<Commodity> findByFarmer(Farmer farmer, int currPage) {
		// TODO Auto-generated method stub
		return commodityDao.findByFarmer(farmer,(currPage-1)*PageConstant.EVERY_COMMODITYPAGE_NUM,PageConstant.EVERY_COMMODITYPAGE_NUM);
	}

	public Commodity findByCommId(int id) {
		// TODO Auto-generated method stub
		return commodityDao.findByCommId(id);
	}

	public CommodityDao getCommodityDao() {
		return commodityDao;
	}

	public void setCommodityDao(CommodityDao commodityDao) {
		this.commodityDao = commodityDao;
	}

}
