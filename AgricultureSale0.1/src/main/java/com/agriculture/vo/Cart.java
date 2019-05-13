package com.agriculture.vo;

public class Cart {
	private int id;
	private Commodity commodity;
	private User user;
	private Integer quantity;
	private double subtotal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
}
