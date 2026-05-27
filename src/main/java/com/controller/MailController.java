package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.MailService;

@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/test-mail")
    public String sendMail() {

        mailService.sendOtp(
            "samosachaat1000@gmail.com",
            "123456"
        );

        return "MAIL_SENT";
    }

}