package com.agriculture.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.agriculture.dao.UserDao;
import com.agriculture.vo.User;

public class UserDaoImpl implements UserDao{

	private SessionFactory sessionFactory;
	
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update User u set u.status = 1 where u.activecode=? ");
		query.setString(0, user.getActivecode());
		query.executeUpdate();
	}
	public User findByActiveCode(String activecode) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User u where u.activecode=? ");
		query.setString(0, activecode);
		User user =  (User) query.uniqueResult();
		return user;
	}
	
	public User login(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User u where u.username=? and u.psw=?");
		query.setString(0, user.getUsername());
		query.setString(1, user.getPsw());
		User login_user =  (User) query.uniqueResult();
		return login_user;
	}

	public User findByName(String username) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User u where u.username=? ");
		query.setString(0, username);
		User user =  (User) query.uniqueResult();
		return user;
	}
	
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User u where u.email=? ");
		query.setString(0, email);
		User user =  (User) query.uniqueResult();
		return user;
	}
	
	public void add(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
    
}
