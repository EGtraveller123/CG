package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Kucun;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface KucunMapper {

    @Select("select * from kucun_t where xs!='0' and s!='0' and m!='0' and l!='0' and xl!='0' and xxl!='0' and xxxl!='0'")
    List<Kucun> selectAll();

    @Select("select kuanhao from kucun_t")
    List<Kucun> selectA();

    @Select("select * from kucun_t where kuanhao=#{kuanhao} and xs!='0' and s!='0' and m!='0' and l!='0' and xl!='0' and xxl!='0' and xxxl!='0'")
    Kucun selectBykuanhao(String kuanhao);

    @Select("insert into kucun_t(kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl) " +
            "values " +
            "(#{kuanhao},#{yanse}," +
            "(select sum(xs) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(xs) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})," +
            "(select sum(s) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(s) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})," +
            "(select sum(m) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(m) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})," +
            "(select sum(l) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(l) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})," +
            "(select sum(xl) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(xl) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})," +
            "(select sum(xxl) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(xxl) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})," +
            "(select sum(xxxl) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(xxxl) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})")
    Kucun insertKucun(String kuanhao);

    @Update("update kucun_t set kuanhao=#{kuanhao},yanse=#{yanse} "+
            "xs=(select sum(xs) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(xs) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})," +
            "s=(select sum(s) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(s) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})," +
            "m=(select sum(m) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(m) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})," +
            "l=(select sum(l) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(l) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})," +
            "xl=(select sum(xl) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(xl) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})," +
            "xxl=(select sum(xxl) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(xxl) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})," +
            "xxxl=(select sum(xxxl) from jincang_t where kuanhao=#{kuanhao} and yanse=#{yanse})-(select sum(xxxl) from chucang_t where kuanhao=#{kuanhao} and yanse=#{yanse})")
    Kucun updatetKucun(String kuanhao);

    @Select("select kuanhao,yanse,cjriqi,xs,s,m,l,xl,xxl,xxxl from caijianbu_t where kuanhao=#{kuanhao}")
    List<Kucun> findByKuanhao(String kuanhao);
}
