package org.com.cay.service;

import org.com.cay.entity.User;

public interface IUserService {

	public User login(String username, String password);
}
