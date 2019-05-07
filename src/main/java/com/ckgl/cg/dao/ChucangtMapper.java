package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Chucangt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ChucangtMapper {

    @Select("select k.kuanhao,k.yanse,j.ccriqi,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl,j.beizhu from chucang_t j join kucun_t k on j.kucunid=k.id")
    List<Map> selectAll();

    @Insert("insert into chucang_t(kucunid,ccriqi,xs,s,m,l,xl,xxl,xxxl,beizhu) values (#{kucunid},#{ccriqi},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{beizhu})")
    boolean insert(Chucangt chucangt);

    @Select("select k.kuanhao,k.yanse,j.ccriqi,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl,j.beizhu from chucang_t j join kucun_t k on j.kucunid=k.id where k.kuanhao=#{kuanhao}")
    List<Map> selectByKuanhao(String kuanhao);

    @Select("select k.kuanhao,k.yanse,j.ccriqi,j.xs,j.s,j.m,j.l,j.xl,j.xxl,j.xxxl,j.beizhu from chucang_t j join kucun_t k on j.kucunid=k.id where k.kuanhao=#{kuanhao} and k.yanse=#{yanse}")
    List<Map> findByKuanhaoYanse(String kuanhao,String yanse);


}
