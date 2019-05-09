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

    @Select("select kuanhao,hdbshuliang from houdaobu")
    List<Map> selectAll();

    @Insert("insert into houdaobu(kuanhao,hdbshuliang) values(#{kuanhao},#{hdbshuliang})")
    boolean insertHoudaobu(Houdaobu caijianbu);

    @Select("select * from houdaobu where kuanhao=#{kuanhao}")
    Houdaobu selectByKuanhao(String kuanhao);

    @Update("update houdaobu set hdbshuliang=#{hdbshuliang} where kuanhao=#{kuanhao}")
    Houdaobu updateHoudaobu(String kuanhao);

    @Select("select * from houdaobu where kuanhao=#{kuanhao}")
    List<Map> selectByKuanhao2(String kuanhao);
}