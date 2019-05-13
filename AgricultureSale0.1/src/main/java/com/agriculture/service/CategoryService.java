package com.agriculture.service;

import java.util.List;

import com.agriculture.vo.Category;

public interface CategoryService {
    List<Category> findAllCate();

	List<Category> findChildCate(int id);
}
