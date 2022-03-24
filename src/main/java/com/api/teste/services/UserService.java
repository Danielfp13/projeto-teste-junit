package com.api.teste.services;

import com.api.teste.domain.User;
import com.api.teste.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
	public User findById(Integer id);

	public List<User> findAll();

	public User insert(UserDTO userDTO);
	
	public User update(UserDTO userDTO);
	
	public void delete(Integer id);
	
}