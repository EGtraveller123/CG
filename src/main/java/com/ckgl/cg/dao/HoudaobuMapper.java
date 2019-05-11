package com.ckgl.cg.dao;


import com.ckgl.cg.bean.Houdaobu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public interface HoudaobuMapper {

    @Select("select id,kuanhao,yanse,cjbshuliang from caijianbu")
    List<Map> selectAll();

    @Insert("insert into houdaobu(caijianbuid,kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl) values (#{caijianbuid},#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insertHoudaobu(Houdaobu houdaobu);

    @Select("select * from houdaobu where caijianbuid=#{id}")
    Houdaobu selectByid(Integer id);

    @Update("update houdaobu set xs=#{xs},s=#{s},#{m},#{l},#{xl},#{xxl},#{xxxl} where caijianbuid=#{caijianbuid}")
    boolean updateHoudaobu(Houdaobu houdaobu);

    @Select("select * from houdaobu_t where kuanhao=#{kuanhao}")
    List<Map> selectByKuanhao2(String kuanhao);

    @Select("select kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl from houdaobu where kuanhao=#{kuanhao} and yanse=#{yanse}")
    Houdaobu selectKuanhaoYanse(String kuanhao,String yanse);

    @Select("select id,kuanhao,sum(cjbshuliang) as cjbshuliang from caijianbu where kuanhao=#{kuanhao}")
    List<Map> selectByYewubu(String kuanhao);
}