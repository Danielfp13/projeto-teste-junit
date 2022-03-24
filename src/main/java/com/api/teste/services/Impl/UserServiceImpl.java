package com.api.teste.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.teste.domain.User;
import com.api.teste.repositories.UserRepository;
import com.api.teste.services.UserService;
import com.api.teste.services.excptions.ObjectNotFoundException;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired

	@Override
	public User findById(Integer id) {

		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException("Usúario com id " + id + " não existe."));
	}

	@Override
	public List<User> findAll() {
		List<User> users =  userRepository.findAll();
		return users;
	}
}