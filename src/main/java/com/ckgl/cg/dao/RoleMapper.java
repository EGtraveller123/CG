package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {

    @Select("select r.* from role r join user_role ur on ur.roleid = r.id where ur.id = #{userid} ")
    List<Role> findRolesByUserId(int userid);
}
