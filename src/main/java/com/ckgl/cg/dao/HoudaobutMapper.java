package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Houdaobu;
import com.ckgl.cg.bean.Houdaobut;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public interface HoudaobutMapper {

    @Select("select * from houdaobu_t")
    List<Map> selectAll();

    @Select("select kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,hdriqi from houdaobu_t where kuanhao=#{kuanhao}")
    List<Map> selectByKuanhao(String kuanhao);

    @Select("select a.kuanhao as ca_kuanhao,a.yanse as ca_yanse,sum(a.xs) as ca_xs,sum(a.s) as ca_s,sum(a.m) as ca_m," +
            "sum(a.l) as ca_l,sum(a.xl) as ca_xl,sum(a.xxl) as ca_xxl,sum(a.xxxl) as ca_xxxl,sum(b.xs) as ho_xs,sum(b.s) as ho_s," +
            "sum(b.m) as ho_m,sum(b.l) as ho_l,sum(b.xl) as ho_xl,sum(b.xxl) as ho_xxl,sum(b.xxxl) as ho_xxxl " +
            "from caijianbu_t a,houdaobu_t b where a.kuanhao=#{kuanhao}")
    List<Map> findByKuanhao(String kuanhao);

    @Insert("insert into houdaobu_t(kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,hdriqi,hdshuliang) " +
            "values(#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{hdriqi},#{hdshuliang})")
    boolean insertHoudaobu(Houdaobu houdaobu);

    @Update("update houdaobu_t set xs=#{xs},s=#{s},m=#{m},#{l},#{xl},#{xxl},#{xxxl} where caijianbuid=#{caijianbuid}")
    boolean updateHoudaobut(Houdaobut houdaobut);
}