package com.spring.login.service;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.spring.login.DTO.RoleDTO;
import com.spring.login.exceptions.HandlerExceptions;
import com.spring.login.models.Role;
import com.spring.login.repository.RoleRepository;
import com.spring.login.response.ResponseJson;
import com.spring.login.service.iservice.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ResponseJson responseJson;

	@Override
	public ResponseEntity<Map<String, Object>> getRoles() {
		// TODO Auto-generated method stub
		try {
			return responseJson.messageOK("Listado de Roles ", roleRepository.findAll());
		} catch (Exception e) {
			throw new HandlerExceptions("Error Leyendo datos de Roles " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			// TODO: handle exception
		}		
	}

	@Override
	public ResponseEntity<Map<String, Object>> getById(Long id) {
		// TODO Auto-generated method stub
		String message = "Rol con Id: " + id;
		try {
			return responseJson.messageOK("Datos del " +message, roleRepository.findById(id)
					.orElseThrow(() -> new HandlerExceptions(message + " no existe", HttpStatus.NOT_FOUND)));		
		} catch (Exception e) {
			throw new HandlerExceptions(e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Map<String, Object>> saveOrUpdate(RoleDTO roleDTO) {
		// TODO Auto-generated method stub

		Role role = new Role();
		role = modelMapper.map(roleDTO, Role.class);
		try {
			return responseJson.messageOK("Datos del Rol " + role.getName(), roleRepository.save(role));
		} catch (Exception e) {
			throw new HandlerExceptions("Error Grabando datos de Roles: " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
			// TODO: handle exception
		}
	}

}
