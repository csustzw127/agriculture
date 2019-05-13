package com.test;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.hibernate.Query;
import org.junit.Test;

import com.agriculture.dao.impl.CategoryDaoImpl;
import com.agriculture.vo.Cart;
import com.agriculture.vo.Category;
import com.agriculture.vo.Commodity;
import com.agriculture.vo.Page;
import com.agriculture.vo.User;
import com.utils.CommonUtils;
import com.utils.HibernateUtil;
import com.utils.Mail;
import com.utils.MailUtils;

public class AgricultureTest {
   @Test
   public void testCategory(){
	   CategoryDaoImpl dao = new CategoryDaoImpl();
	   List<Category> list = dao.findAll();
	   for(int i=0;i<list.size();i++) {
		   System.out.println(list.get(i).getName());
		   Set<Category> childs = list.get(i).getChilds();
		   Iterator<Category> iterator = childs.iterator() ;
		   while(iterator.hasNext()) {
			   Category category = iterator.next();
			   System.out.println("   " + category.getName());
			   Set<Category> childs2 = category.getChilds();
			   Iterator<Category> iterator2 = childs2.iterator() ;
			   while(iterator2.hasNext()) {
				   Category category2 = iterator2.next();
				   System.out.println("      " + category2.getName());
			   }
		   }
	   }
	      
   }
   @Test
   public void testMail(){
	   
	    User user = new User();
	    user.setActivecode(CommonUtils.uuid());
	    user.setEmail("593136267@qq.com");
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String host = prop.getProperty("host");
		String name = prop.getProperty("username");
		String pass = prop.getProperty("password");
	    Session session = MailUtils.createSession(host, name, pass);
	    
	    String from = prop.getProperty("from");
	    String to = user.getEmail();
	    String subject = prop.getProperty("subject");
	    String content = prop.getProperty("content");
	    content = MessageFormat.format(content, user.getActivecode());
	    
	    Mail mail = new Mail(from, to, subject, content);
	    try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
   }
   
   @Test
   public void testAAA() {
	   org.hibernate.Session session = HibernateUtil.getSession();
	   int start=0;
	   int num =3;
	   int u_id = 4;
	   StringBuilder sql = new StringBuilder("select pro_id from cart where u_id=?");
		//1、查询出所有的购物车条目的产品的id
		Query query = session.createSQLQuery(sql.toString());
		query.setInteger(0, u_id);
		List proIdList = query.list();
		
		//2、查询出这些产品属于那些农户
		int index = proIdList.size();
		sql = new StringBuilder("select distinct(fid) from product where pro_id in (");
		//根据产品id数来动态添加 ？ 数量
		for(int i=0;i<index;i++) {
			if( i == index-1)
				sql.append("?)");
			else 
				sql.append("?,");
		}
		query = session.createSQLQuery(sql.toString());
		for(int i=0;i<index;i++) {
			query.setInteger(i, Integer.parseInt(proIdList.get(i).toString()));
		}
		//这个集合存放了用户购物车产品的农户id
		List<Integer> farmerIdList = query.list();
		
		//3、根据这个id 来查询同一个店铺的购物车
		sql = new StringBuilder("select * from cart where pro_id in (select id from product where fid=?)");
		query = session.createSQLQuery(sql.toString());
		
		//4、分页思路   ------ 页面上是将每个农户的产品放在一起进行显示，所以一页有几个就是几个农户
		List<ArrayList<Cart>> list = new ArrayList<ArrayList<Cart>>();
		
		ArrayList<Cart> listCart;
		List result;
		
       index = farmerIdList.size();
       int i = start*num;         //num = 每页有几个农户
		for(int j=0;j<num ; j++,i++) {
			query.setInteger(0, farmerIdList.get(i));
			result = query.list();
			//封装Cart信息
			listCart = new ArrayList<Cart>();
			getListCart(result,listCart);
			
			//将listCart装入list中
			list.add(listCart);
		}
		Page<ArrayList<Cart>> page = new Page<ArrayList<Cart>>();
		
		page.setCurrPage(start);
		page.setEverypage(num);
		
		int totalNum = farmerIdList.size();
		if(totalNum % page.getEverypage() == 0) {
			page.setTotalPage(totalNum/page.getEverypage());
		} else {
			page.setTotalPage(totalNum/page.getEverypage() + 1);
		}
   }
   private void getListCart(List result, List<Cart> listCart) {
		// TODO Auto-generated method stub
		Cart cart;
		for(int i=0;i<result.size();i++) {
			Object[] obj = (Object[]) result.get(i);
			cart = new Cart();
			Commodity comm = new Commodity();
			User user = new User();
			
			cart.setId((Integer) obj[0]);
			
			comm.setId((Integer) obj[1]);
		    cart.setCommodity(comm);
		    
		    user.setId((Integer) obj[2]);
			cart.setUser(user);
			
			cart.setQuantity((Integer) obj[3]);
			cart.setSubtotal((Double) obj[4]);
			
			//将cart加入listCart
			listCart.add(cart);
		}
			
	}
}



