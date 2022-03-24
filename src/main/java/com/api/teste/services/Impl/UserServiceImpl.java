package com.api.teste.services.Impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.teste.domain.User;
import com.api.teste.domain.dto.UserDTO;
import com.api.teste.repositories.UserRepository;
import com.api.teste.services.UserService;
import com.api.teste.services.excptions.DataIntegrityViolationException;
import com.api.teste.services.excptions.ObjectNotFoundException;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;
	
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
	public User insert(UserDTO userDTO) {
		findByEmail(userDTO);
		return userRepository.save(mapper.map(userDTO, User.class));
	}
	
	private void findByEmail(UserDTO userDTO) {
		Optional<User> user = userRepository.findByEmail(userDTO.getEmail());
		if(user.isPresent() && !user.get().getId().equals(userDTO.getId())) {
			throw new DataIntegrityViolationException("Já existe usuário com esse email");
		}
	
	}

	@Override
	public User update(UserDTO userDTO) {
		findById(userDTO.getId());
		findByEmail(userDTO);
		return userRepository.save(mapper.map(userDTO, User.class));
	}
}