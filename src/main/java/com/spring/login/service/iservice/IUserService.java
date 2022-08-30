package com.spring.login.service.iservice;



import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.spring.login.DTO.UserDTO;
import com.spring.login.models.User;

public interface IUserService {
	
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    List<User> getClients();
    ResponseEntity<Map<String, Object>> saveUser(UserDTO userDTO);

}
