package com.api.teste.services;

import com.api.teste.domain.User;
import com.api.teste.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    public User findById(Integer id);

    public void delete(Integer id);

    public User insert(UserDTO userDTO);

    public List<User> findAll();


    public User update(UserDTO userDTO);
}