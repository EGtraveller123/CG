package com.ckgl.cg.service;

import com.ckgl.cg.bean.User;
import com.ckgl.cg.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

//    public int insert(String username,String userpwd){
//        User user = new User();
//        user.setUsername(username);
//        user.setUserpwd(userpwd);
//        return userMapper.insert(user);
//    }

    public List<User> findAll(){
        return userMapper.findAll();
    }

    public String inster(User user){
        User user1 = new User();
        user1.setUsername(String.valueOf(userMapper.findAll().size()+1));
        user1.setPassword(user.getPassword());
        return "success";
    }

    public User login(User user){
        user = this.userMapper.login(user.getUsername(),user.getPassword());
        return user;
    }

    @Cacheable(cacheNames = "authority", key = "#username")
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }
}
