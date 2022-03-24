package com.api.teste.services;

import java.util.List;

import com.api.teste.domain.User;

public interface UserService {
	public User findById(Integer id);

	public List<User> findAll();

	public User insert(User user);
}