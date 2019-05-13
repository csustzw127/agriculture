package com.agriculture.service.impl;

import java.util.List;

import com.agriculture.dao.CategoryDao;
import com.agriculture.service.CategoryService;
import com.agriculture.vo.Category;

public class CategoryServiceImpl implements CategoryService{

	private CategoryDao categoryDao;
	
	public List<Category> findChildCate(int id) {
		// TODO Auto-generated method stub
		return categoryDao.findChildCate(id);
	}

	public List<Category> findAllCate() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	

}
