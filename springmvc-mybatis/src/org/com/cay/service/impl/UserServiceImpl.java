package org.com.cay.service.impl;

import org.com.cay.entity.User;
import org.com.cay.mapper.UserMapper;
import org.com.cay.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public final class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return userMapper.login(username, password);
	}

}
