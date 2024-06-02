package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.pojo.User;

@Mapper
public interface UserMapper {
	@Select("select * from user")
	public List<User> findAllUsers();
	
	@Select("select * from user where username = #{userName}")
	public User findUserByUserName(String userName);
}
