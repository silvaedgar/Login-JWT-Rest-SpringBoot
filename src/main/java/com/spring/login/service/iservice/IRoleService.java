package com.spring.login.service.iservice;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.spring.login.DTO.RoleDTO;

public interface IRoleService {
	
	ResponseEntity<Map<String, Object>> getRoles();
	ResponseEntity<Map<String, Object>> getById(Long id);
	ResponseEntity<Map<String, Object>> saveOrUpdate(RoleDTO roleDTO); 

}
