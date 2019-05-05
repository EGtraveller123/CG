package com.ckgl.cg.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/test")
@PreAuthorize("hasRole('USER')")
public class TestController {

    public String test(){
        return"/test";
    }
}
