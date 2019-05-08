package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Jincangt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface JincangtMapper {

    @Select("select j.kuanhao,j.yanse,j.jcriqi,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl,j.beizhu from jincang_t j")
    List<Map> selectAll();

    @Insert("insert into jincang_t(kucunid,kuanhao,yanse,jcriqi,xs,s,m,l,xl,xxl,xxxl,beizhu) values (#{kucunid},#{kuanhao},#{yanse},#{jcriqi},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{beizhu})")
    boolean insert(Jincangt jincangt);

    @Select("select j.kuanhao,j.yanse,j.jcriqi,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl,j.beizhu from jincang_t j where j.kuanhao=#{kuanhao}")
    List<Map> selectByKuanhao(String kuanhao);

    @Select("select j.kuanhao,j.yanse,j.jcriqi,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl,j.beizhu from jincang_t j join kucun_t k on j.kucunid=k.id where k.kuanhao=#{kuanhao} and k.yanse=#{yanse}")
    List<Map> findByKuanhaoYanse(String kuanhao,String yanse);
}
