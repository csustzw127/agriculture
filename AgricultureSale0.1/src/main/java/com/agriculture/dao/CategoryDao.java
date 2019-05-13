package com.agriculture.dao;

import java.util.List;

import com.agriculture.vo.Category;

public interface CategoryDao {
    List<Category> findAll();

	List<Category> findChildCate(int id);
}
