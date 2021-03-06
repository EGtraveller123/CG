package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Jincangt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface JincangtMapper {

    @Select("select j.kuanhao,j.yanse,j.jcriqi,j.xxs,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl,j.beizhu from jincang_t j order by id desc")
    List<Map> selectAll();

    @Insert("insert into jincang_t(kucunid,kuanhao,yanse,jcriqi,xxs,xs,s,m,l,xl,xxl,xxxl,beizhu) values (#{kucunid},#{kuanhao},#{yanse},#{jcriqi},#{xxs},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{beizhu})")
    boolean insert(Jincangt jincangt);

    @Select("select j.kuanhao,j.yanse,j.jcriqi,j.xxs,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl,j.beizhu from jincang_t j where j.kuanhao=#{kuanhao} order by id desc")
    List<Map> selectByKuanhao(String kuanhao);

    @Select("select j.kuanhao,j.yanse,j.jcriqi,j.xxs,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl,j.beizhu from jincang_t j where j.kuanhao=#{kuanhao} and j.yanse=#{yanse}")
    List<Map> findByKuanhaoYanse(String kuanhao,String yanse);
}
