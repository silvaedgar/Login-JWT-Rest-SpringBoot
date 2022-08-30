package com.spring.login.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.login.DTO.UserDTO;
import com.spring.login.exceptions.HandlerExceptions;
import com.spring.login.models.User;
import com.spring.login.repository.RoleRepository;
import com.spring.login.repository.UserRepository;
import com.spring.login.response.ResponseJson;
import com.spring.login.service.iservice.IUserService;

@Service
public class UserService implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	ResponseJson responseJson;

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Optional<User> findByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> findByUsernameOrEmail(String username, String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getClients() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public ResponseEntity<Map<String, Object>> saveUser(UserDTO userDTO) {

		User user = new User();
		user = modelMapper.map(userDTO, User.class);
		logger.info("USER: {} {} {}", user.getUsername(), user.getId(), user.getRoles());

		try {
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//			user.setRoles(new HashSet<>(userDTO.getRoles()));
			user = getRolesUser(userDTO, user);
			logger.info("USER TRY: {} " + userDTO.getRoles());
			return responseJson.messageOK("Datos de Usuario grabados con exito", userRepository.save(user));
		} catch (Exception e) {
			// TODO: handle exception
			throw new HandlerExceptions("Error de datos, grabando usuario" + e.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		}
	}

	private User getRolesUser(UserDTO userDTO, User user) {
		for (int i = 0; i < userDTO.getRoles().size(); i++) {
//			logger.info("ROL: {} {}",  userDTO.getRoles().get(i).getId(), user.getRoles().get(i).getId());
//			Role rol = roleRepository.findById(userDTO.getRoles().get(i).getId())
//					.orElseThrow(() -> new HandlerExceptions("NO EXISTE ROL", HttpStatus.NOT_FOUND));
			user.setRoles(null);	
			
//			user.getRoles().get(i).setId(rol.getId());
//			user.getRoles().get(i).setName(rol.getName());
			
//			user.addRole(rol);
		}
		return user;
	}

}
