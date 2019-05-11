package com.ckgl.cg.dao;
import com.ckgl.cg.bean.Houdaobut;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public interface HoudaobutMapper {

    @Select("select * from houdaobu_t")
    List<Map> selectAll();

    @Select("select kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,hdriqi from houdaobu_t where kuanhao=#{kuanhao} and yanse=#{yanse}")
    List<Map> selectByKuanhaoYanse(@Param("kuanhao")String kuanhao,@Param("yanse") String yanse);

    @Select("select a.kuanhao as ho_kuanhao,a.yanse as ho_yanse,a.xs as ca_xs,a.s as ca_s,a.m as ca_m,a.l as ca_l,a.xl as ca_xl,a.xxl as ca_xxl,a.xxxl as ca_xxxl," +
            "b.xs as ho_xs,b.s as ho_s,b.m as ho_m,b.l as ho_l,b.xl as ho_xl,b.xxl as ho_xxl,b.xxxl as ho_xxxl " +
            "from caijianbu a left join houdaobu b on a.id=b.caijianbuid where a.id=#{id}")
    List<Map> findById(Integer id);

    @Insert("insert into houdaobu_t(kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,hdriqi,beizhu) " +
            "values(#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{hdriqi},#{beizhu})")
    boolean insertHoudaobut(Houdaobut houdaobut);


}