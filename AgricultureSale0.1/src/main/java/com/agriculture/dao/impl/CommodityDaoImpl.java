package com.agriculture.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.agriculture.dao.CommodityDao;
import com.agriculture.vo.Commodity;
import com.agriculture.vo.Condition;
import com.agriculture.vo.Farmer;
import com.agriculture.vo.Page;
import com.agriculture.vo.PageConstant;

public class CommodityDaoImpl implements CommodityDao{
  
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Commodity findByCommId(int commodityId) {
		Session session = sessionFactory.getCurrentSession();
		Commodity comm = (Commodity)session.get(Commodity.class, commodityId);
		return comm;
	}
	public Page<Commodity> findByCategory(int start, int num, int cate_id) {
		// TODO Auto-generated method stub
		String hql = "from Commodity as commodity";
		Map<String,Object> map = new HashMap<String,Object>();
		List<Condition> list = new ArrayList<Condition>();
		Condition condition = new Condition();
		condition.setName("commodity.category.id");
		condition.setOperation("=");
		condition.setValue(cate_id);
		list.add(condition);
		Page<Commodity> page = findByPage(hql,start,num,list);
		return page;
	}
	
	public Page<Commodity> findByName(String name,int i, int num) {
		// TODO Auto-generated method stub
		String hql = "from Commodity as commodity";
		List<Condition> list = new ArrayList<Condition>();
		Condition condition = new Condition();
		condition.setName("commodity.name");
		condition.setOperation("like");
		condition.setValue("%"+name+"%");
		
		list.add(condition);
		Page<Commodity> page = findByPage(hql,i,num,list);
		return page;
	}

	public Page<Commodity> findByFarmer(Farmer farmer, int start,int num) {
		// TODO Auto-generated method stub
		String hql = "from Commodity as commodity";
		Map<String,Object> map = new HashMap<String,Object>();
		//添加查询条件
		List<Condition> list = new ArrayList<Condition>();
		Condition condition = new Condition();
		condition.setName("farmer.id");
		condition.setOperation("=");
		condition.setValue(farmer.getId());
		list.add(condition);
		//使用封装好的查询条件进行分页查询
		Page<Commodity> page = findByPage(hql,start,num,list);
		return page;
	}
	private Page<Commodity> findByPage(String hql, int start, int num,
			List<Condition> params) {
		// TODO Auto-generated method stub
		Page<Commodity> page = new Page<Commodity>();
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
		page.setEverypage(PageConstant.EVERY_COMMODITYPAGE_NUM);
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
	
	/*
	 * (non-Javadoc)
	 * @see com.agriculture.dao.CommodityDao#add(com.agriculture.vo.Commodity)
	 * 添加产品
	 */
	public void add(Commodity comm) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(comm);
	}
	
	
}
