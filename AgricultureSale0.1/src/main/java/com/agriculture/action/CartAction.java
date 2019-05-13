package com.agriculture.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.agriculture.service.CartService;
import com.agriculture.vo.Cart;
import com.agriculture.vo.Commodity;
import com.agriculture.vo.Page;
import com.agriculture.vo.User;
import com.opensymphony.xwork2.Action;

public class CartAction {
	private CartService cartService;
	private int uid;
	private int currPage;
	private int commodityId;
	private double price;
	private int quantity;
	private int cartId;
	private String cartIds;
	
	public String addToCart() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null)
			return "nologin";
		Cart cart = new Cart();
		cart.setUser(user);
		Commodity comm = new Commodity();
		comm.setId(commodityId);
		cart.setCommodity(comm);
		cart.setSubtotal(price * quantity);
		cart.setQuantity(quantity);
		
		cartService.addToCart(cart,user.getId(),1);
		
		return "success";
	}
	
	public String delete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null)
			return "nologin";
		cartService.deleteByIds(cartIds);
		Page<ArrayList<Cart>> page = cartService.findByUser(user.getId(), currPage);
		request.setAttribute("page", page);
		return "success";
	}
	
	public String findByUser() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user == null)
			return "nologin";
		Page<ArrayList<Cart>> page = cartService.findByUser(user.getId(), currPage);
		request.setAttribute("page", page);
		return "success";
	}
	public CartService getCartService() {
		return cartService;
	}
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public String getCartIds() {
		return cartIds;
	}

	public void setCartIds(String cartIds) {
		this.cartIds = cartIds;
	}
	
}
