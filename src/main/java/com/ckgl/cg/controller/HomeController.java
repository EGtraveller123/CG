package com.ckgl.cg.controller;

import com.ckgl.cg.bean.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DELL on 2019/4/28.
 */
@Controller
public class HomeController {

    @RequestMapping("/mjfs")
    public String index(){
        return "mjfs";
    }
}