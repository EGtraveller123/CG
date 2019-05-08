package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Houdaobut;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public interface HoudaobutMapper {
    /**
     * 添加houdaobut信息
     * @param houdaobut
     */
    @Insert("insert into houdaobu_t(kuanhao,yanse,hdriqi,xs,s,m,l,xl,xxl,xxxl,beizhu) values (#{kuanhao},#{yanse},#{hdriqi},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{beizhu})")
    boolean insert(Houdaobut houdaobut);

    /**
     * 选择指定 款号
     * @param kuanhao
     * @return 返回指定对应的所有信息
     */
    @Select("select kuanhao,yanse,hdriqi,xs,s,m,l,xl,xxl,xxxl,beizhu from houdaobu_t where kuanhao=#{kuanhao}")
    Houdaobut selectByKuanhao(String kuanhao);

    @Select("select b.kuanhao as ho_kuanhao,b.yanse as ho_yanse,b.hdriqi as ho_hdriqi,sum(a.xs) as ca_xs,sum(a.s) as ca_s," +
            "sum(a.m) as ca_m,sum(a.l) as ca_l,sum(a.xl) as ca_xl,sum(a.xxl) as ca_xxl,sum(a.xxxl) as ca_xxxl," +
            "sum(b.xs) as ye_xs,sum(b.s) as ye_s,sum(b,m) as ye_m,sum(b.l) as ye_l,sum(b.xl) as ye_xl,sum(b.xxl) as ye_xxl," +
            "sum(b.xxxl) as ye_xxxl from caijianbu_t a,houdaobu_t b where b.kuanhao=#{kuanhao}")
    List<Map> findByKuanhao(String kuanhao);
}