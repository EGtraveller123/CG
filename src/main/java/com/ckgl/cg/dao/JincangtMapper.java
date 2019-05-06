package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Jincangt;
import com.ckgl.cg.bean.Kucunt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface JincangtMapper {

    /**
     *
     * @param jincangt
     * @return
     */
    @Insert("insert into jincang_t(kuanhao,yanse,jcriqi,xs,s,m,l,xl,xxl,xxxl) values (#{kuanhao},#{yanse},#{jcriqi},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insert(Jincangt jincangt);

    @Insert("insert into kucun_t(kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl) values (#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insertKucunt(Kucunt kucunt);

    @Update("update kucun_t set xs=#{xs},set s=#{s},set m=#{m},set l=#{l},set xl=#{xl},set xxl=#{xxl},set xxxl=#{xxxl} where kuanhao = #{kuanhao}")
    boolean updateKucunt(Kucunt kucunt);

    /**
     *
     * @param kuanhao
     * @return
     */
    @Select("select kuanhao,yanse,jcriqi,xs,s,m,l,xl,xxl,xxxl from jincang_t where kuanhao=#{kuanhao}")
    Jincangt selectByKuanhao(String kuanhao);

    @Select("select kuanhao,yanse,jcriqi,xs,s,m,l,xl,xxl,xxxl from jincang_t where kuanhao=#{kuanhao}")
    List<Jincangt> findByKuanhao(String kuanhao);
}
