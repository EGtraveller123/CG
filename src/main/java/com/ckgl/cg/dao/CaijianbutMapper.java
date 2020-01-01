package com.ckgl.cg.dao;
import com.ckgl.cg.bean.Caijianbut;
import com.ckgl.cg.bean.Yewubu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public interface CaijianbutMapper {

    @Select("select * from caijianbu_t")
    List<Map> selectAll();

    @Select("select kuanhao,yanse,xxs,xs,s,m,l,xl,xxl,xxxl from yewubu where id=#{id}")
    Yewubu selectById(Integer id);

    @Select("select id,kuanhao,yanse,xxs,xs,s,m,l,xl,xxl,xxxl,cjriqi from caijianbu_t where kuanhao=#{kuanhao} and yanse=#{yanse}")
    List<Map> selectByKuanhaoYanse(String kuanhao,String yanse);

    @Select("select a.kuanhao as ca_kuanhao,a.yanse as ca_yanse ,a.xxs as ye_xxs,a.xs as ye_xs,a.s as ye_s,a.m as ye_m,a.l as ye_l,a.xl as ye_xl,a.xxl as ye_xxl,a.xxxl as ye_xxxl," +
            "b.xxs as ca_xxs,b.xs as ca_xs,b.s as ca_s,b.m as ca_m,b.l as ca_l,b.xl as ca_xl,b.xxl as ca_xxl,b.xxxl as ca_xxxl " +
            "from yewubu a left join caijianbu b on a.id=b.yewubuid where a.id=#{id}")
    List<Map> findById(Integer id);

    @Insert("insert into caijianbu_t(kuanhao,yanse,xxs,xs,s,m,l,xl,xxl,xxxl,cjriqi) " +
            "values(#{kuanhao},#{yanse},#{xxs},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{cjriqi})")
    boolean insertCaijiant(Caijianbut caijianbut);

    @Update("update caijianbu_t set kuanhao=#{kuanhao},yanse=#{yanse},cjriqi=#{cjriqi},xxs=#{xxs},xs=#{xs},s=#{s},m=#{m},l=#{l},xl=#{xl},xxl=#{xxl},xxxl=#{xxxl} where id=#{id}")
    boolean updateCaijianbut(Caijianbut caijianbut);

    @Select("select * from caijianbu_t where id=#{id}")
    Caijianbut selectCaijianbutById(int id);
}