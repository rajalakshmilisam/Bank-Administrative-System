package com.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.demo.dao.UserRepository;
import com.demo.dto.UserDTO;
import com.demo.dto.UserLoginDTO;
import com.demo.entity.User;
import com.demo.response.LoginResponse;

@Component
public class UserImplements implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String addUser(UserDTO userDTO) {

		User newUser = new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getEmail(),
				this.passwordEncoder.encode(userDTO.getPassword()), userDTO.getGender());

		userRepository.save(newUser);

		return newUser.getUserName();
	}

	@Override
	public LoginResponse loginUser(UserLoginDTO userLoginDTO) {

		String temporary_message = "";

		User existingUser = userRepository.findByEmail(userLoginDTO.getEmail());
		if (existingUser != null) {

			String password = userLoginDTO.getPassword();
			String encodedPassword = existingUser.getPassword();

			Boolean isPasswordRight = passwordEncoder.matches(password, encodedPassword);
			if (isPasswordRight) {
				Optional<User> user = userRepository.findByEmailAndPassword(userLoginDTO.getEmail(), encodedPassword);
				if (user.isPresent()) {
					return new LoginResponse("Login Successfull", true);
				} else {
					return new LoginResponse("Login failed", false);
				}
			} else {
				return new LoginResponse("Password not match", false);
			}
		} else {
			return new LoginResponse("Email not exist", false);
		}
	}

}
