package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Kucun;
import com.ckgl.cg.bean.Kucunt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface KucunMapper {

    @Select("select * from kucun_t where kuanhao=#{kuanhao} and yanse=#{yanse}")
    Kucunt findByKuanhaoYanse(String kuanhao,String yanse);

    @Select("select * from kucun")
    List<Kucun> selectAll();

    @Insert("insert into kucun(kuanhao,kcshuliang) values(#{kuanhao},'0')")
    boolean insertKucun(Kucun kucun);

    @Update("update kucun set kcshuliang=(select sum(xs+s+m+l+xl+xxl+xxxl) from kucun_t where kuanhao=#{kuanhao})")
    boolean updateKucun(Kucun kucun);

    @Select("select * from kucun_t where kuanhao=#{kuanhao}  and (select sum(xs+s+m+l+xl+xxl+xxxl)!='0')")
    Kucun selectBykuanhao(String kuanhao);

    @Select("select * from kucun")
    Kucun selectKuanhao(String kuanhao);

    @Insert("insert into kucun(kuanhao,kcshuliang) values (#{kuanhao},(select sum(xs+s+m+l+xl+xxl+xxxl) from kucun_t where kuanhao=#{kuanhao}))")
    boolean insert(Kucun kucun);

    @Select("select kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl from kucun_t where kuanhao=#{kuanhao}")
    List<Kucunt> findByKuanhao(String kuanhao);

    //进仓
    @Insert("insert into kucun_t(kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl) values (#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insertJC(Kucunt kucunt);

    //出仓
    @Insert("insert into kucun_t(kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl) values (#{kuanhao},#{yanse},-#{xs},-#{s},-#{m},-#{l},-#{xl},-#{xxl},-#{xxxl})")
    boolean insertCC(Kucunt kucunt);

    //求kuncun_t的和
    @Select("select kuanhao,yanse,sum(xs),sum(s),sum(m),sum(l),sum(xl),sum(xxl),sum(xxxl) from kucun_t where kuanhao=#{kuanhao} and yanse=#{yanse}")
    List<Kucunt> selectKucuntBykuanhaoyanse(String kuanhao);
}
