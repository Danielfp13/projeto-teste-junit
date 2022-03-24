package com.api.teste.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

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
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> findAll() {
		return ResponseEntity.ok().body( userService.findAll()
				.stream().map( (x) -> mapper.map(x, UserDTO.class)).collect(Collectors.toList()));
	}
	
	@PostMapping()
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO userDTO){
		User user = mapper.map(userDTO, User.class);
		user = userService.insert(user);
		userDTO = mapper.map(user, UserDTO.class);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(userDTO);
	}
}
