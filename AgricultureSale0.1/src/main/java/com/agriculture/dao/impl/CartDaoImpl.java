package com.agriculture.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.agriculture.dao.CartDao;
import com.agriculture.vo.Cart;
import com.agriculture.vo.Commodity;
import com.agriculture.vo.Condition;
import com.agriculture.vo.Farmer;
import com.agriculture.vo.Page;
import com.agriculture.vo.PageConstant;
import com.agriculture.vo.User;

public class CartDaoImpl implements CartDao {

	private SessionFactory sessionFactory;
	
	public void deleteByIds(int[] ids) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		StringBuilder sql = new StringBuilder("delete from cart where id in (");
		//根据id数来动态添加 ？ 数量
		for(int i=0;i<ids.length;i++) {
			if( i == ids.length-1)
				sql.append("?)");
			else 
				sql.append("?,");
		}
		Query query = session.createSQLQuery(sql.toString());
		for(int i=0;i<ids.length;i++) {
			query.setInteger(i, ids[i]);
		}
        query.executeUpdate();
	}

	public void updateCart(Cart cart) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String hql = "update Cart as cart set cart.quantity =?,cart.subtotal =? where cart.id=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, cart.getQuantity());
		query.setDouble(1, cart.getSubtotal());
		query.setInteger(2, cart.getId());
		query.executeUpdate();
	}

	public Page<ArrayList<Cart>> findByUser(int u_id,int start,int num) {
		StringBuilder sql = new StringBuilder("from Cart cart where cart.user.id=?");
		//1、查询出所有的购物车条目的产品的id
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(sql.toString());
		query.setInteger(0, u_id);
		List<Cart> cartList = query.list();
		
		//2、查询出这些产品属于那些农户
		int index = cartList.size();
		sql = new StringBuilder("select distinct(fid) from product where id in (");
		//根据产品id数来动态添加 ？ 数量
		for(int i=0;i<index;i++) {
			if( i == index-1)
				sql.append("?)");
			else 
				sql.append("?,");
		}
		query = session.createSQLQuery(sql.toString());
		for(int i=0;i<index;i++) {
			query.setInteger(i, cartList.get(i).getCommodity().getId());
		}
		//这个集合存放了用户购物车产品的农户id
		List<Integer> farmerIdList = query.list();
		//3、查询出所有的产品信息
		sql = new StringBuilder("from Commodity comm where comm.id in (");
		//根据产品id数来动态添加 ？ 数量
		for(int i=0;i<index;i++) {
			if( i == index-1)
				sql.append("?)");
			else 
				sql.append("?,");
		}
		query = session.createQuery(sql.toString());
		for(int i=0;i<index;i++) {
			query.setInteger(i, cartList.get(i).getCommodity().getId());
		}
		List<Commodity> commList = query.list();
		
		//4、根据这个id 来查询同一个店铺的购物车
		sql = new StringBuilder("select * from cart where pro_id in (select id from product where fid=?)");
		query = session.createSQLQuery(sql.toString());
		
		//4、分页思路   ------ 页面上是将每个农户的产品放在一起进行显示，所以一页有几个就是几个农户
		List<ArrayList<Cart>> list = new ArrayList<ArrayList<Cart>>();
		
		ArrayList<Cart> listCart;
		List result;
		
        index = farmerIdList.size();
        int i = start*num;         //num = 每页有几个农户,根据start是第几页，得到从列表第几个开始
        Query query2 = session.createSQLQuery("select nickname from farmer where id=?");
        //j 表示的是从第一行记录开始 所以 j应该等于 start*num
		for(int j=0;j<num && j<farmerIdList.size(); j++,i++) {
			query.setInteger(0, farmerIdList.get(i));
			query2.setInteger(0, farmerIdList.get(i));
			result = query.list();
			Object nickname = query2.uniqueResult();
			//封装Cart信息
			listCart = new ArrayList<Cart>();
			getListCart(result,listCart,commList,nickname);
			
			//将listCart装入list中
			list.add(listCart);
		}
		Page<ArrayList<Cart>> page = new Page<ArrayList<Cart>>();
		
		page.setPageList(list);
		page.setCurrPage(start);
		page.setEverypage(num);
		
		int totalNum = farmerIdList.size();
		if(totalNum % page.getEverypage() == 0) {
			page.setTotalPage(totalNum/page.getEverypage());
		} else {
			page.setTotalPage(totalNum/page.getEverypage() + 1);
		}
		
		return page;
	}
	
	private void getListCart(List result, List<Cart> listCart,List<Commodity> commList,Object nickname) {
		// TODO Auto-generated method stub
		Cart cart;
		Farmer farmer = new Farmer();
		farmer.setNickname(nickname.toString());
		for(int i=0;i<result.size();i++) {
			Object[] obj = (Object[]) result.get(i);
			cart = new Cart();
			Commodity comm = new Commodity();
			User user = new User();
			
			cart.setId(Integer.parseInt(obj[0].toString()));
			
			int commId = Integer.parseInt(obj[1].toString());
			for(int j=0;j<commList.size();j++)
				if(commId == commList.get(j).getId()) {
					comm = commList.get(j);
					break;
				}
			comm.setFarmer(farmer);
		    cart.setCommodity(comm);
		    
		    user.setId(Integer.parseInt(obj[2].toString()));
			cart.setUser(user);
			
			cart.setQuantity(Integer.parseInt(obj[3].toString()));
			cart.setSubtotal(Double.parseDouble(obj[4].toString()));
			
			//将cart加入listCart
			listCart.add(cart);
		}
			
	}

	public Cart findByComm(Commodity commodity,int uid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
//		Session session = HibernateUtil.getSession();
		String hql = "from Cart as cart where cart.commodity.id=? and cart.user.id=?";
		Query query = session.createQuery(hql);
		query.setInteger(0, commodity.getId());
		query.setInteger(1, uid);
		List<Cart> list = query.list();
		if(list.size() == 0)
			return null;
		else 
			return list.get(0);
	}
	public void addCart(Cart cart) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(cart);
	}
	
	private Page<Cart> findByPage(String hql, int start, int num,
			List<Condition> params) {
		// TODO Auto-generated method stub
		Page<Cart> page = new Page<Cart>();
		//获取根据params查询条件查询出的结果共有多少条
		int totalNum = getTotalNumByCondition(hql, params);
		Session session = sessionFactory.getCurrentSession();
		/*
		 * 获取hql
		 */
		String _hql = getHql(hql,params);
		Query query = session.createQuery(_hql);
		/*
		 * 添加查询条件数据
		 */
		int index=0;
		for(Condition condition : params) {
			query.setParameter(index, condition.getValue());
			index++;
		}
		//hibernate封装好的分页查询方法
		query.setFirstResult(start);
		query.setMaxResults(num);
		
		page.setPageList(query.list());
		page.setEverypage(PageConstant.EVERY_CARTPAGE_NUM);
		page.setTotalPage(totalNum);
		
		//设置共有多少页
		if(totalNum % page.getEverypage() == 0) {
			page.setTotalPage(totalNum/page.getEverypage());
		} else {
			page.setTotalPage(totalNum/page.getEverypage() + 1);
		}
		
		return page;
	}
	private int getTotalNumByCondition(String hql,
			List<Condition> params) {
		Session session = sessionFactory.getCurrentSession();
		String _hql = getHql(hql,params);
		_hql = "select count(*) " + _hql;
		Query query = session.createQuery(_hql);
		
		int i=0;
		for(Condition condition : params) {
			query.setParameter(i, condition.getValue());
			i++;
		}
		
		String num = query.uniqueResult().toString();
		return Integer.parseInt(num);
	}
	private String getHql(String hql, List<Condition> params) {
		// TODO Auto-generated method stub
		if(params.size() <=0 ) return hql;
		StringBuilder _hql = new StringBuilder(hql);
		_hql.append(" where ");
		int i=0;
		for(Condition condition : params) {
			_hql.append(condition.getName()).append(" ");
			_hql.append(condition.getOperation()).append(" ");
			_hql.append("?").append(" ");
			if(i!=params.size()-1)
				_hql.append(" and ");
		}
		return _hql.toString();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
