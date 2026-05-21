package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dto.LoginRequest;

@RestController
@CrossOrigin("*")
public class LoginController {

@PostMapping("/login")
public ResponseEntity<String> login(
@RequestBody LoginRequest req
){

if(
req.getUsername().equals("admin")
&&
req.getPassword().equals("1234")
){

return ResponseEntity.ok(
"Login Success");

}

return ResponseEntity
.status(401)
.body("Invalid Credentials");

}

}