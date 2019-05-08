package com.ckgl.cg.dao;
import com.ckgl.cg.bean.Caijianbut;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public interface CaijianbutMapper {
    /**
     * 添加caijianbut信息
     * @param caijianbut
     */
    @Insert("insert into caijianbu_t(kuanhao,yanse,cjriqi,xs,s,m,l,xl,xxl,xxxl) values (#{kuanhao},#{yanse},#{cjriqi},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insert(Caijianbut caijianbut);

    /**
     * 选择指定 款号
     * @param kuanhao
     * @return 返回指定对应的所有信息
     */
    @Select("select kuanhao,yanse,cjriqi,xs,s,m,l,xl,xxl,xxxl from caijianbu_t where kuanhao=#{kuanhao}")
    Caijianbut selectByKuanhao(String kuanhao);

    @Select("select a.kuanhao as ca_kuanhao,a.yanse as ca_yanse,a.cjriqi as ca_cjriqi,sum(a.xs) as ca_xs,sum(a.s) as ca_s," +
            "sum(a.m) as ca_m,sum(a.l) as ca_l,sum(a.xl) as ca_xl,sum(a.xxl) as ca_xxl,sum(a.xxxl) as ca_xxxl," +
            "sum(b.xs) as ye_xs,sum(b.s) as ye_s,sum(b,m) as ye_m,sum(b.l) as ye_l,sum(b.xl) as ye_xl,sum(b.xxl) as ye_xxl," +
            "sum(b.xxxl) as ye_xxxl from caijianbu_t a,yewubu_t b where a.kuanhao=#{kuanhao}")
    List<Map> findByKuanhao(String kuanhao);

    @Select("select sum(xs),SUM(s),sum(m),SUM(l),SUM(xl),SUM(xxl),SUM(xxxl) from caijianbu_t where kuanhao=#{kuanhao} and yanse=#{yanse}")
    List<Caijianbut> selectA(String kuanhao);
}