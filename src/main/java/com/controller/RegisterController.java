package com.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.LoginUser;
import com.entity.OtpData;
import com.repository.LoginUserRepository;
import com.service.MailService;

@RestController
@CrossOrigin("*")
public class RegisterController {

    @Autowired
    private LoginUserRepository repository;

    @Autowired
    private MailService mailService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private Map<String, OtpData> pendingUsers =
            new HashMap<>();

    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestBody LoginUser user) {

        if (repository.existsByEmail(
                user.getEmail())) {

            return ResponseEntity
                    .badRequest()
                    .body(
                    "EMAIL_ALREADY_EXISTS");
        }

        String otp =
                String.valueOf(
                100000 +
                new Random()
                .nextInt(900000));

        OtpData data =
                new OtpData();

        data.setEmail(
                user.getEmail());

        data.setPassword(

                encoder.encode(

                        user.getPassword()

                )

        );

        data.setOtp(
                otp);

        pendingUsers.put(
                user.getEmail(),
                data);

        mailService.sendOtp(
                user.getEmail(),
                otp);

        return ResponseEntity.ok(
                "OTP_SENT");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(
            @RequestBody OtpData request) {

        OtpData saved =
                pendingUsers.get(
                request.getEmail());

        if (saved == null) {

            return ResponseEntity
                    .badRequest()
                    .body(
                    "OTP_EXPIRED");
        }

        if (!saved.getOtp()
                .equals(
                request.getOtp())) {

            return ResponseEntity
                    .badRequest()
                    .body(
                    "INVALID_OTP");
        }

        LoginUser user =
                new LoginUser();

        user.setEmail(
                saved.getEmail());

        user.setPassword(
                saved.getPassword());

        repository.save(
                user);

        pendingUsers.remove(
                saved.getEmail());

        return ResponseEntity.ok(
                "ACCOUNT_CREATED");
    }

}