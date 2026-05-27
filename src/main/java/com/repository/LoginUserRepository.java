package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.LoginUser;

public interface LoginUserRepository
extends JpaRepository<LoginUser, Long>{

Optional<LoginUser>
findByEmail(
String email
);

boolean existsByEmail(
String email
);

}