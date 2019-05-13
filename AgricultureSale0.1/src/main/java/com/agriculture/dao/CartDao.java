package com.agriculture.dao;

import java.util.ArrayList;

import com.agriculture.vo.Cart;
import com.agriculture.vo.Commodity;
import com.agriculture.vo.Page;

public interface CartDao {

	public Cart findByComm(Commodity commodity, int uid);

	public void addCart(Cart cart);

	public void updateCart(Cart cart);

	public Page<ArrayList<Cart>> findByUser(int uid, int i, int num);

	public void deleteByIds(int[] ids);

}
