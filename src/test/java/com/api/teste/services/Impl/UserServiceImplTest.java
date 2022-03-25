package com.api.teste.services.Impl;

import com.api.teste.domain.User;
import com.api.teste.domain.dto.UserDTO;
import com.api.teste.repositories.UserRepository;
import com.api.teste.services.excptions.DataIntegrityViolationException;
import com.api.teste.services.excptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NOME = "Ana";
    public static final String EMAIL = "ana@email.com";
    public static final String PASSWORD = "123";

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenfindByIdThenReturnAnUserIntance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NOME,response.getNome());
        assertEquals(EMAIL,response.getEmail());

    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(ID)).thenThrow(
                new ObjectNotFoundException("Usúario com id " + ID + " não existe."));
        try {
            service.findById(ID);
        }catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Usúario com id " + ID + " não existe.", e.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));
        List<User> response = service.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());

        assertEquals(ID, response.get(0).getId());
        assertEquals(NOME, response.get(0).getNome());
        assertEquals(EMAIL, response.get(0).getEmail());
        assertEquals(PASSWORD, response.get(0).getPassword());
    }

    @Test
    void whenInsertThenReturnAnUserSucess() {
        when(repository.save(any())).thenReturn(user);

        User response = service.insert(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenInsertThenReturnAnDataIntegrityViolationException() {
       when(repository.findByEmail(anyString())).thenReturn(optionalUser);
       try {
           optionalUser.get().setId(2);
           service.insert(userDTO);
       }catch (Exception e){
           assertEquals(DataIntegrityViolationException.class, e.getClass());
           assertEquals("Já existe usuário com esse email", e.getMessage());
       }
    }

    @Test
    void whenUpdatethenReturnAnUserSucess() {
        when(repository.save(any())).thenReturn(user);
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdatetThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        try {
            optionalUser.get().setId(2);
            service.update(userDTO);
        }catch (Exception e){
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals("Já existe usuário com esse email", e.getMessage());
        }
    }

    @Test
    void whenDeleteWithSucess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(anyInt());

        service.delete(ID);
        verify(repository, times(1)).deleteById(anyInt());
    }

    private void startUser(){
         user =  new User(ID, NOME, EMAIL, PASSWORD);
         userDTO =  new UserDTO(ID,NOME,EMAIL, PASSWORD);
         optionalUser =  Optional.of(new User(ID,NOME, EMAIL, PASSWORD));

    }
}