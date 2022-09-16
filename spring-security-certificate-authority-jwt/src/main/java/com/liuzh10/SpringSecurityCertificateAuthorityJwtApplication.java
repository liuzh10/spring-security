package com.liuzh10;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuzh10
 * @desc Certificate Authority jwt security test
 */
@SpringBootApplication
@MapperScan("com.liuzh10.mapper")
public class SpringSecurityCertificateAuthorityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityCertificateAuthorityJwtApplication.class, args);
    }
}
