package com.ckgl.cg.controller;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.User;
import com.ckgl.cg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "login";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers() {
        return userService.findAll();
    }

    public JSONObject login(@RequestBody String username,String password){
        JSONObject jsonObject = new JSONObject();
        User user= userService.findByUserName(username);
        if(user != null){
            if (user.getPassword().equals(password)){
                jsonObject.put("result","success");
            }else {
                jsonObject.put("result","incorrectCredentials");
            }
        }else {
            jsonObject.put("result","error");
        }
        return jsonObject;
    }

//    @RequestMapping(value = "/registerUser",method = RequestMethod.GET)
//    public int insert(@RequestParam("username") String username,
//                      @RequestParam("userpwd") String userpwd){
//        return userService.insert(username,userpwd);
//    }

//    @RequestMapping(value = "logi")
//    @PreAuthorize("hasRole('ADMIN')") // Spring Security默认的角色前缀是”ROLE_”,使用hasRole方法时已经默认加上了
//    public String login(User user, HttpServletRequest request, HttpSession session){
//        ModelAndView modelAndView = new ModelAndView();
//        String username = request.getParameter("username");
//        String userpwd = request.getParameter("userpwd");
//        user.setUsername(username);
//        user.setUserpwd(userpwd);
//        user = this.userService.login(user);
//        if(user!=null){
//            session.setAttribute("user",user);
//            //这里是主页面
//            modelAndView.setViewName("/pages/mjfs.html");
//            return modelAndView;
//        }else{
//            session.setAttribute("error","账号或密码错误!请重新输入");
//        }
//        modelAndView.setViewName("/login");
//        return "houdaobu";


}
