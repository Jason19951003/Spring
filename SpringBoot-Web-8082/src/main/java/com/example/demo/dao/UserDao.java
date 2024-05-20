package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.po.User;

public interface UserDao {

	List<User> findAllUsers();
	
	User getUser(Integer id);	
}
