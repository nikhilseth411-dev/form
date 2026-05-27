package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtp(
            String email,
            String otp) {

        SimpleMailMessage mail =
                new SimpleMailMessage();

        mail.setTo(email);

        mail.setSubject(
                "Union Bank Verification");

        mail.setText(
                "Your OTP is: " + otp);

        mailSender.send(mail);

    }

}