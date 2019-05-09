package com.ckgl.cg.dao;


import com.ckgl.cg.bean.Caijianbu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public interface CaijianbuMapper {

    @Select("select * from caijianbu")
    List<Map> selectAll();

//    @Insert("insert into caijianbu(kuanhao,cjbshuliang) values(#{kuanhao},#{cjbshuliang})")
//    boolean insertCaijianbu(Caijianbu caijianbu);

    @Insert("insert into caijianbu(yewubuid,kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl) values (#{yewubuid},#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insertcaijianbu(Caijianbu caijianbu);

    @Select("select * from caijianbu where kuanhao=#{kuanhao}")
    Caijianbu selectByKuanhao(String kuanhao);

//    @Update("update caijianbu set cjbshuliang=#{cjbshuliang} where kuanhao=#{kuanhao}")
//    Caijianbu updateCaijianbu(String kuanhao);

    @Update("update caijianbu set xs=#{xs},s=#{s},#{m},#{l},#{xl},#{xxl},#{xxxl} where yewubuid=#{yewubuid}")
    boolean updateCaijianbu(Caijianbu caijianbu);

    @Select("select * from caijianbu where kuanhao=#{kuanhao}")
    List<Map> selectByKuanhao2(String kuanhao);
}