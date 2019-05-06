package com.ckgl.cg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ckgl.cg.dao")
@SpringBootApplication
public class CgApplication {

    public static void main(String[] args) {
        SpringApplication.run(CgApplication.class, args);
    }

}
