package com.api.teste.config;

import com.api.teste.domain.User;
import com.api.teste.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("test")
public class LocalConfig {

	@Autowired
	private UserRepository repository;
	
	@Bean
	public void startDB() {
		User user1 = new User(null, "Daniela", "daniela@email.com", "123");
		User user2 = new User(null, "Natalia", "natalia@email.com", "123");
		User user3 = new User(null, "Luana", "luana@email.com", "123");
		
		repository.saveAll(List.of(user1,user2,user3));
	}
}
