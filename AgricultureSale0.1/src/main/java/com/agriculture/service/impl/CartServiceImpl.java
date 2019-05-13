package com.agriculture.service.impl;

import java.util.ArrayList;

import com.agriculture.dao.CartDao;
import com.agriculture.service.CartService;
import com.agriculture.vo.Cart;
import com.agriculture.vo.Page;
import com.agriculture.vo.PageConstant;

public class CartServiceImpl implements CartService {

	CartDao cartDao;
	public void addToCart(Cart cart, int uid, int currPage) {

		// 1、该商品是否已经加入购物车
		Cart exit_cart = cartDao.findByComm(cart.getCommodity(), uid);

		if (exit_cart == null) {
			cartDao.addCart(cart);
			return;
		}
		// 2、改变购物车的数量和小结就可以了
		cart.setQuantity(cart.getQuantity() + exit_cart.getQuantity());
		cart.setSubtotal(cart.getSubtotal() + exit_cart.getSubtotal());
		cart.setId(exit_cart.getId());
		cartDao.updateCart(cart);
	}
	
	public void deleteByIds(String cartIds) {
		// TODO Auto-generated method stub
		String[] arrcartIds = cartIds.split(",");
		int[] ids = new int[arrcartIds.length];
		for(int i=0;i<arrcartIds.length;i++)
			ids[i] = Integer.parseInt(arrcartIds[i]);
		cartDao.deleteByIds(ids);
	}

	public Page<ArrayList<Cart>> findByUser(int uid,int currPage) {
		int num = PageConstant.EVERY_CARTPAGE_NUM;
		Page<ArrayList<Cart>> page = cartDao.findByUser(uid, (currPage-1)*num, num);
		page.setCurrPage(currPage);
		page.setUrl("cart/findByUser.action?");
		return page;
	}
	public CartDao getCartDao() {
		return cartDao;
	}
	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}
}
