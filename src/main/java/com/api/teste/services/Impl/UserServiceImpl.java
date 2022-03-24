package com.api.teste.services.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.teste.domain.User;
import com.api.teste.repositories.UserRepository;
import com.api.teste.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findById(Integer id) {

		Optional<User> obj = userRepository.findById(id);
		return obj.orElse(null);
	}
}