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

    @Select("select DISTINCT a.kuanhao as ca_kuanhao,a.yanse as ca_yanse,sum(a.xs) as ye_xs,sum(a.s) as ye_s,sum(a.m) as ye_m," +
            "sum(a.l) as ye_l,sum(a.xl) as ye_xl,sum(a.xxl) as ye_xxl,sum(a.xxxl) as ye_xxxl,sum(b.xs) as ca_xs,sum(b.s) as ca_s," +
            "sum(b.m) as ca_m,sum(b.l) as ca_l,sum(b.xl) as ca_xl,sum(b.xxl) as ca_xxl,sum(b.xxxl) as ca_xxxl " +
            "from yewubu a, caijianbu b  where a.id=#{id} or b.yewubuid=#{id}")
    List<Map> findById(Integer id);

    @Insert("insert into houdaobu_t(kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,hdriqi,beizhu) " +
            "values(#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{hdriqi},#{beizhu})")
    boolean insertHoudaobut(Houdaobut houdaobut);


}