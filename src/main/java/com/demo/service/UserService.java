package com.demo.service;

import org.springframework.stereotype.Service;

import com.demo.dto.UserDTO;
import com.demo.dto.UserLoginDTO;
import com.demo.response.LoginResponse;

@Service
public interface UserService {

	String addUser(UserDTO userDTO);

	LoginResponse loginUser(UserLoginDTO userLoginDTO);

}
