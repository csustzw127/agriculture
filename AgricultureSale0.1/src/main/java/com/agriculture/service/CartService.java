package com.agriculture.service;

import java.util.ArrayList;

import com.agriculture.vo.Cart;
import com.agriculture.vo.Page;

public interface CartService {

	void addToCart(Cart cart, int id, int i);

	Page<ArrayList<Cart>> findByUser(int id, int currPage);

	void deleteByIds(String cartIds);

}
