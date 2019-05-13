package com.agriculture.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.agriculture.dao.CategoryDao;
import com.agriculture.vo.Category;
import com.utils.HibernateUtil;

public class CategoryDaoImpl implements CategoryDao {

	private SessionFactory sessionFactory;
	
	public List<Category> findChildCate(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Category as category where category.parent.id = ?");
		query.setInteger(0, id);
        return query.list();
	}

	public List<Category> findAll() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Category as category where category.parent is null");
        return query.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

}
