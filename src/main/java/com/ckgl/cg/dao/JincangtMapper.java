package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Jincangt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JincangtMapper {

    /**
     *
     * @param jincangt
     * @return
     */
    @Insert("insert into jincang_t(kuanhao,yanse,jcriqi,xs,s,m,l,xl,xxl,xxxl) values (#{kuanhao},#{yanse},#{jcriqi},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insert(Jincangt jincangt);

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
