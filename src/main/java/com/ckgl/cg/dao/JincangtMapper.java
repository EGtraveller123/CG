package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Jincangt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface JincangtMapper {

    @Select("select k.kuanhao,k.yanse,j.jcriqi,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl from jincangt j join kucun_t t on j.kucunid=k.id")
    List<Map> selectAll();

    @Insert("insert into jincang_t(kucunid,jcriqi,xs,s,m,l,xl,xxl,xxxl,beizhu) values (#{kucunid},#{jcriqi},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{beizhu})")
    boolean insert(Jincangt jincangt);

    @Select("select k.kuanhao,k.yanse,j.jcriqi,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl from jincang_t j join kucun_t k on j.kucunid=k.id where k.kuanhao=#{kuanhao}")
    List<Map> selectByKuanhao(String kuanhao);

    @Select("select k.kuanhao,k.yanse,j.jcriqi,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl from jincang_t j join kucun_t k on j.kucunid=k.id where k.kuanhao=#{kuanhao} and k.yanse=#{yanse}")
    List<Map> findByKuanhaoYanse(String kuanhao,String yanse);
}
