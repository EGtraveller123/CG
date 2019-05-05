package com.ckgl.cg.service;

import com.ckgl.cg.bean.Role;
import com.ckgl.cg.dao.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    public List<Role> findRolesByUserId(int userid){
        return roleMapper.findRolesByUserId(userid);
    }
}
