package com.liuzh10;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuzh10
 * @desc Certificate Authority Form security test
 */
@SpringBootApplication
@MapperScan("com.liuzh10.mapper")
public class SpringSecurityCertificateAuthorityBackFrontFormApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityCertificateAuthorityBackFrontFormApplication.class, args);
    }
}
