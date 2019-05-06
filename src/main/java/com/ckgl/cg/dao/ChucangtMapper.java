package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Chucangt;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ChucangtMapper {

    @Insert("insert into chucang_t(kuanhao,yanse,ccriqi,xs,s,m,l,xl,xxl,xxxl) values (#{kuanhao},#{yanse},#{ccriqi},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insert(Chucangt chucangt);

    @Update("update kucun_t set ccriqi=#{ccriqi},set xs=#{xs},set s=#{s},set m=#{m},set l=#{l},set xl=#{xl},set xxl=#{xxl},set xxxl=#{xxxl} where kuanhao = #{kuanhao}")
    boolean updateKucunt(String kuanhao);
    /**
     *
     * @param kuanhao
     * @return
     */
    @Select("select kuanhao,yanse,ccriqi,xs,s,m,l,xl,xxl,xxxl from chucang_t where kuanhao=#{kuanhao}")
    Chucangt selectByKuanhao(String kuanhao);

    @Select("select kuanhao,yanse,ccriqi,xs,s,m,l,xl,xxl,xxxl from chucang_t where kuanhao=#{kuanhao}")
    List<Chucangt> findByKuanhao(String kuanhao);
}
