package com.api.teste.resources;

import com.api.teste.domain.User;
import com.api.teste.domain.dto.UserDTO;
import com.api.teste.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
		User user = userService.insert(userDTO);
		userDTO = mapper.map(user, UserDTO.class);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(userDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable Integer id){
		userDTO.setId(id);
		User user = userService.update(userDTO);
		return ResponseEntity.ok().body(mapper.map(user, UserDTO.class));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Integer id){
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
