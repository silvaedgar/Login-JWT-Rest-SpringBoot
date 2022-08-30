package com.spring.login.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.login.DTO.RoleDTO;
import com.spring.login.service.RoleService;

@RestController
@RequestMapping("/roles/")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping
	public ResponseEntity<Map<String, Object>> getAllRoles() {
		
		return roleService.getRoles();
		
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")	
	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getRolById(@PathVariable Long id) {
		
		return roleService.getById(id);
	}

	@PreAuthorize("hasRole('ADMIN')")		
	@PostMapping
	public ResponseEntity<Map<String, Object>> saveRole(@RequestBody RoleDTO roleDTO) {
		
		return roleService.saveOrUpdate(roleDTO);
	}

}
