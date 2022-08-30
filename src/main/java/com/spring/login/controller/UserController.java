package com.spring.login.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.login.DTO.UserDTO;
import com.spring.login.models.User;
import com.spring.login.service.UserService;


@RestController
@RequestMapping("/users")
@CrossOrigin("*")     // esta senetncia se usa por el cross desactivado para ver que erros da o funciona asi

public class UserController {

	@Autowired
	UserService userService;
	
	@PreAuthorize("hasAnyRole('ADMIN','USER','CLIENT')")
	@GetMapping
	public List<User> getAllUsers() {
		
		return userService.getClients();
		
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> saveOrUpdateClient(@RequestBody UserDTO userDTO) {
		return userService.saveUser(userDTO);
	}
}
