package com.api.teste.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.teste.domain.User;
import com.api.teste.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Configuration
@Profile("test")
@AllArgsConstructor
public class LocalConfig {

	private UserRepository repository;
	
	@Bean
	public void startDB() {
		User user1 = new User(null, "Daniela", "daniela@email.com", "123");
		User user2 = new User(null, "Natalia", "natalia@email.com", "123");
		User user3 = new User(null, "Luana", "luana@email.com", "123");
		
		repository.saveAll(List.of(user1,user2,user3));
	}
}
