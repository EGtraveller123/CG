package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRoleMapper {
    @Select("select * from  role where userId=#{id}")
    List<Role> getRoleByUser(Long id);
}
