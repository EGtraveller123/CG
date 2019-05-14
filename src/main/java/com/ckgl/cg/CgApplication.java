package com.ckgl.cg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.ckgl.cg.dao")
@SpringBootApplication
public class CgApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CgApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CgApplication.class);
    }

}
