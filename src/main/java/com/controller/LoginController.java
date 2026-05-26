package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.LoginRequest;

@RestController
@CrossOrigin("*")

public class LoginController {

	@PostMapping("/login")

	public ResponseEntity<String> login(

			@RequestBody LoginRequest req

	) {

		System.out.println("EMAIL = " + req.getEmail());

		System.out.println("PASSWORD = " + req.getPassword());

		if (

		"user@gmail.com".equals(req.getEmail())

				&&

				"bank123".equals(req.getPassword())

		) {

			return ResponseEntity.ok("Login Success");

		}

		return ResponseEntity.status(401).body("Invalid Credentials");

	}

}