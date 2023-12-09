package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.UserDTO;
import com.demo.dto.UserLoginDTO;
import com.demo.response.LoginResponse;
import com.demo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/registeruser")
	public String registerUser(@RequestBody UserDTO userDTO) {
		String registerNewUser = userService.addUser(userDTO);
		return registerNewUser;
	}

	@PostMapping("/loginuser")
	public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLoginDTO) {

		LoginResponse loginResponse = userService.loginUser(userLoginDTO);
		return ResponseEntity.ok(loginResponse);
	}
}
