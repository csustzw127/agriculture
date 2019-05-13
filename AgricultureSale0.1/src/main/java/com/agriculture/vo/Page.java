package com.agriculture.vo;
import java.util.List;

public class Page <T>{

	private int currPage;
	private int totalPage;
	private int everypage;
	private List<T> pageList;
	private String url;
	
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getEverypage() {
		return everypage;
	}
	public void setEverypage(int everypage) {
		this.everypage = everypage;
	}
	public List<T> getPageList() {
		return pageList;
	}
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
