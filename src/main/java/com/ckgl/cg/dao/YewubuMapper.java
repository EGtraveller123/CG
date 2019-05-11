package com.ckgl.cg.dao;


import com.ckgl.cg.bean.Yewubu;
import com.ckgl.cg.bean.Yewubut;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface YewubuMapper {

    @Insert("insert into yewubu(kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,ywbshuliang) values (#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{ywbshuliang})")
    boolean insertYewubu(Yewubu yewubu);

    @Select("select kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl from yewubu where kuanhao=#{kuanhao} and yanse=#{yanse}")
    Yewubu selectByKuanhaoYanse(String kuanhao,String yanse);

    @Update("update yewubu set xs=#{xs},s=#{s},m=#{m},l=#{l},xl=#{xl},xxl=#{xxl},xxxl=#{xxxl} where kuanhao=#{kuanhao} and yanse=#{yanse}")
    boolean updateYewubu(Yewubu yewubu);

//    @Select("select * from yewubu where kuanhao=#{kuanhao} and yanse=#{yanse}")
//    Yewubu selectByKuanhao(String kuanhao,String yanse);
}
