package com.api.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.teste.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
