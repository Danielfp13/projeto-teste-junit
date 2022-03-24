package com.api.teste.resources;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.teste.config.ModelMapperConfig;
import com.api.teste.domain.User;
import com.api.teste.domain.dto.UserDTO;
import com.api.teste.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Integer id) {
		UserDTO userDTO = mapper.map(userService.findById(id), UserDTO.class);
		return ResponseEntity.ok().body(userDTO);

	}

}
