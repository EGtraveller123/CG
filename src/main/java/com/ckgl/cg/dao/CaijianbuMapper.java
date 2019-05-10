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

    @Select("select id,kuanhao,sum(ywbshuliang) as ywbshuliang from yewubu")
    List<Map> selectAll();

    @Insert("insert into caijianbu(yewubuid,kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl) values (#{yewubuid},#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insertcaijianbu(Caijianbu caijianbu);

    @Select("select * from caijianbu where Yewubuid=#{yewubuid}")
    Caijianbu selectYewubuid(int yewubuid);

    @Update("update caijianbu set xs=#{xs},s=#{s},#{m},#{l},#{xl},#{xxl},#{xxxl} where yewubuid=#{yewubuid}")
    boolean updateCaijianbu(Caijianbu caijianbu);

    @Select("select * from caijianbu where kuanhao=#{kuanhao}")
    List<Map> selectByKuanhao2(String kuanhao);

    @Select("select kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl from caijianbu where kuanhao=#{kuanhao} and yanse=#{yanse}")
    Caijianbu selectKuanhaoYanse(String kuanhao,String yanse);

    @Select("select id,kuanhao,sum(ywbshuliang) as ywbshuliang from yewubu where kuanhao=#{kuanhao}")
    List<Map> selectByYewubu(String kuanhao);
}