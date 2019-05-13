package com.agriculture.dao;

import com.agriculture.vo.User;

public interface UserDao {
   User findByName(String username);

   void add(User user);

   User findByEmail(String email);

   User findByActiveCode(String activecode);

   void updateUser(User user);

   User login(User user);
}
