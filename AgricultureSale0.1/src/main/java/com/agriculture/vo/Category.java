package com.agriculture.vo;

import java.util.List;
import java.util.Set;

/**
 * 
 * @author ZhouWei 类别实体，为农产品的类别
 */

public class Category {
	private int id;                       //类别id
	private String name;                  //类别的名称
	private Category parent;              //该类别的父类别
	private Set<Category> childs;        //该类别的子类别集合

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChilds() {
		return childs;
	}

	public void setChilds(Set<Category> childs) {
		this.childs = childs;
	}

}
