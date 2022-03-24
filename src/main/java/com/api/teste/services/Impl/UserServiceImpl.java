package com.api.teste.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.teste.domain.User;
import com.api.teste.repositories.UserRepository;
import com.api.teste.services.UserService;
import com.api.teste.services.excptions.DataIntegrityViolationException;
import com.api.teste.services.excptions.ObjectNotFoundException;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

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

	@Override
	public User insert(User user) {
		findByEmail(user.getEmail());
		return userRepository.save(user);
	}
	
	private void findByEmail(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isPresent()) {
			throw new DataIntegrityViolationException("Já existe usuário com esse email");
		}
	
	}
}