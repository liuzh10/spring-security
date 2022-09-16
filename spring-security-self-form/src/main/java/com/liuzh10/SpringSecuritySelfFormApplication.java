package com.liuzh10;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuzh10
 * @desc self Form security test
 */
@RestController
@SpringBootApplication
public class SpringSecuritySelfFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecuritySelfFormApplication.class, args);
    }

    @GetMapping("/")
    public String hello() {
        return "Hello,Spring security!";
    }

}
