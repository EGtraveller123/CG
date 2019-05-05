package com.ckgl.cg.dao;


import com.ckgl.cg.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface UserMapper {
//    //注册
//    int insert(User user);

    //登录
    User login(String username, String userpwd);

    List<User> findAll();

    User insert(User user);

//    @Select("select u.*,r.role from user u LEFT JOIN user_role sru on u.id= sru.userid LEFT JOIN role r on sru.roleid=r.id where username= #{username}")
    User findByUserName(String username);

}
