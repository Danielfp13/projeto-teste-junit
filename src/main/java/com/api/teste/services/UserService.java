package com.api.teste.services;

import java.util.List;

import com.api.teste.domain.User;
import com.api.teste.domain.dto.UserDTO;

public interface UserService {
	public User findById(Integer id);

	public List<User> findAll();
}