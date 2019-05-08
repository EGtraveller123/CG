package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Chucangt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ChucangtMapper {

    @Select("select j.kuanhao,j.yanse,j.ccriqi,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl,j.beizhu from chucang_t j")
    List<Map> selectAll();

    @Insert("insert into chucang_t(kucunid,kuanhao,yanse,ccriqi,xs,s,m,l,xl,xxl,xxxl,beizhu) values (#{kucunid},#{kuanhao},#{yanse},#{ccriqi},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{beizhu})")
    boolean insert(Chucangt chucangt);

    @Select("select j.kuanhao,j.yanse,j.ccriqi,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl,j.beizhu from chucang_t j where j.kuanhao=#{kuanhao}")
    List<Map> selectByKuanhao(String kuanhao);

    @Select("select j.kuanhao,j.yanse,j.ccriqi,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl,j.beizhu from chucang_t j where j.kuanhao=#{kuanhao} and j.yanse=#{yanse}")
    List<Map> findByKuanhaoYanse(String kuanhao,String yanse);


}
