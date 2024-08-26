package com.todolist.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.todolist.todo.model.User;
import com.todolist.todo.repository.UserRepository;
import com.todolist.todo.util.JWTTokenUtil;

import java.util.Optional;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final JWTTokenUtil jwtTokenUtil;

	@Autowired
	public UserService(UserRepository userRepository, JWTTokenUtil jwtTokenUtil) {
		this.userRepository = userRepository;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Optional<String> loginUser(String email, String password) {
		Optional<User> userOptional = findUserByEmail(email);
		if (userOptional.isPresent()) {
			User user = userOptional.get();

			// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			// if (encoder.matches(password, user.getPassword())) {
			String token = jwtTokenUtil.generateToken(user);
			return Optional.of(token);
			/*
			 * }
			 * 
			 * else {
			 * 
			 * }
			 */
		}

		else {

		}
		return Optional.empty();
	}
}
