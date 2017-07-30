package org.com.cay.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.com.cay.entity.User;

public interface UserMapper {

	@Select("select * from userbean where name=#{username} and password=#{password}")
	public User login(@Param("username")String username, @Param("password")String password);
}
