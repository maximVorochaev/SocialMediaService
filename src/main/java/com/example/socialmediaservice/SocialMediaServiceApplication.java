package com.example.socialmediaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocialMediaServiceApplication {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        SpringApplication.run(SocialMediaServiceApplication.class, args);
    }

}
