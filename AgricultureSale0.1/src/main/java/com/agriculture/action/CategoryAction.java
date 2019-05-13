package com.agriculture.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.agriculture.service.CategoryService;
import com.agriculture.vo.Category;
import com.opensymphony.xwork2.Action;

public class CategoryAction {
	
	private CategoryService categoryService;
	private int id;
	
	public String findAll() {
		List<Category> allCategory = categoryService.findAllCate();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("allCategory", allCategory);
		return "success";
	}
	/*
	 * ajax请求顶级分类的响应
	 */
	public String findParentCate() {
		List<Category> list = categoryService.findAllCate();
		JSONArray jsonArr = new JSONArray();
        JSONObject jsonArea;
        for(int i=0;i<list.size();i++) {
           jsonArea = new JSONObject();
   		   jsonArea.put("id", list.get(i).getId());
   		   jsonArea.put("name", list.get(i).getName());
   		   jsonArr.add(jsonArea);
        }
        printJSON(jsonArr);
		return Action.NONE;
	}
	/*
	 * ajax请求次级分类的响应
	 */
	public String findChildCate() {
		List<Category> list = categoryService.findChildCate(id);
		JSONArray jsonArr = new JSONArray();
        JSONObject jsonArea;
        for(int i=0;i<list.size();i++) {
           jsonArea = new JSONObject();
   		   jsonArea.put("id", list.get(i).getId());
   		   jsonArea.put("name", list.get(i).getName());
   		   jsonArr.add(jsonArea);
        }
        printJSON(jsonArr);
		return Action.NONE;
	}
	
	public String allCategory() {
		List<Category> list = categoryService.findAllCate();
		JSONArray jsonArr = new JSONArray();
        JSONObject jsonArea;
        JSONObject jsonArea1;
        JSONObject jsonArea2;
        JSONArray jsonchild1;
        JSONArray jsonchild2;
        for(int i=0;i<list.size();i++) {
 		   System.out.println(list.get(i).getName());
 		   
 		   //构造与category结构类似的json对象，一个jsonArea相当于一个顶层category
 		   //在本项目中共有三层类别
 		   jsonArea = new JSONObject();
 		   jsonArea.put("id", list.get(i).getId());
 		   jsonArea.put("name", list.get(i).getName());
 		   jsonchild1 = new JSONArray();
 		   
 		   //遍历出第二层
 		   Set<Category> childs = list.get(i).getChilds();
 		   Iterator<Category> iterator = childs.iterator();
 		   while(iterator.hasNext()) {
 			   Category category = iterator.next();
 			   System.out.println("   " + category.getName());
 			
 			   //封装第二层的category，jsonArea1对应第二层的每一个分类
 			   jsonArea1 = new JSONObject();
 	 		   jsonArea1.put("id", category.getId());
 	 		   jsonArea1.put("name", category.getName());
 	 		   //jsonchild2封装该分类对应第三层的子类
 	 		   jsonchild2 = new JSONArray();
 	 		   
 			   
 			   Set<Category> childs2 = category.getChilds();
 			   Iterator<Category> iterator2 = childs2.iterator() ;
 			   while(iterator2.hasNext()) {
 				   Category category2 = iterator2.next();
 				   System.out.println("      " + category2.getName());
 				   
 				   //封装第三层子类别
 				   jsonArea2 = new JSONObject();
 	 	 		   jsonArea2.put("id", category2.getId());
 	 	 		   jsonArea2.put("name", category2.getName());
 	 	 		   
 	 	 		   //将第三层的子类别装入对应的父类别数组中
 	 	 		   jsonchild2.add(jsonArea2);
 			   }
 			   //添加该第二层分类的第三层子类别
 			   jsonArea1.put("childs", jsonchild2);
 			   //将该类添加到父类别中
 			   jsonchild1.add(jsonArea1);
 		   }
 		  //添加顶层类别的子类别
 		  jsonArea.put("childs",jsonchild1);
 		  //将该顶层类别的json对象加入json数组中
 		  jsonArr.add(jsonArea);
 	   }
        printJSON(jsonArr);
        System.out.println(jsonArr.toString());
    	return Action.NONE;
	}
	
	private void printJSON(JSONArray jsonArr) {
		HttpServletResponse response = ServletActionContext.getResponse();
    	response.setCharacterEncoding("utf-8");
    	try {
			response.getWriter().println(jsonArr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
