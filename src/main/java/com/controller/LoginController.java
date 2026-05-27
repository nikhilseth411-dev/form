package com.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.LoginUser;
import com.repository.LoginUserRepository;

import dto.LoginRequest;

@RestController
@CrossOrigin("*")

public class LoginController {

	@Autowired
	private LoginUserRepository repository;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest request) {

		Optional<LoginUser> user = repository.findByEmail(request.getEmail());

		if (!user.isPresent()) {
			return ResponseEntity.status(401).body("USER_NOT_FOUND");
		}

		if (!user.get().getPassword().equals(request.getPassword())) {

			return ResponseEntity.status(401).body("INVALID_PASSWORD");
		}

		return ResponseEntity.ok("LOGIN_SUCCESS");
	}

}