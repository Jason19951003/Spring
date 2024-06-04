package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.pojo.User;

@Mapper
public interface UserRequestMapper {
	
	@Select("select * from user_info where user_id = #{userId}")
	public User findUserByUserName(String userId);
}
