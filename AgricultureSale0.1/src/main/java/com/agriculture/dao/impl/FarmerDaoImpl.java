package com.agriculture.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.agriculture.dao.FarmerDao;
import com.agriculture.vo.Farmer;
import com.agriculture.vo.User;

public class FarmerDaoImpl implements FarmerDao {
	
	private SessionFactory sessionFactory;
	
	public Farmer login(Farmer farmer) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Farmer f where f.username=? and f.psw=?");
		query.setString(0, farmer.getUsername());
		query.setString(1, farmer.getPsw());
		Farmer login_farmer =  (Farmer) query.uniqueResult();
		return login_farmer;
	}

	public Farmer findByName(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Farmer f where f.username=? ");
		query.setString(0, username);
		Farmer farmer =  (Farmer) query.uniqueResult();
		return farmer;
	}

	public void add(Farmer farmer) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(farmer);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
