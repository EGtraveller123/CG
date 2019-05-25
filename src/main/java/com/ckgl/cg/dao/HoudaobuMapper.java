package com.ckgl.cg.dao;


import com.ckgl.cg.bean.Houdaobu;
import com.ckgl.cg.bean.Houdaobut;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public interface HoudaobuMapper {

    @Select("select a.id,a.kuanhao,a.yanse,a.cjbshuliang,b.hdbshuliang as hdzonghe from caijianbu a LEFT JOIN houdaobu b on a.id=b.caijianbuid order by ${sortName} ${sortOrder}")
    List<Map> selectAll(@Param("sortName") String sortName, @Param("sortOrder") String sortOrder);

    @Insert("insert into houdaobu(caijianbuid,kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,hdbshuliang) values (#{caijianbuid},#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{hdbshuliang})")
    boolean insertHoudaobu(Houdaobu houdaobu);

    @Select("select * from houdaobu where caijianbuid=#{id}")
    Houdaobu selectByid(Integer id);

    @Update("update houdaobu set xs=#{xs},s=#{s},m=#{m},l=#{l},xl=#{xl},xxl=#{xxl},xxxl=#{xxxl},hdbshuliang=#{hdbshuliang} where caijianbuid=#{caijianbuid}")
    boolean updateHoudaobu(Houdaobu houdaobu);

    @Select("select a.id,a.kuanhao,a.yanse,a.cjbshuliang,b.hdbshuliang as hdzonghe from caijianbu a LEFT JOIN houdaobu b on a.id=b.caijianbuid where a.kuanhao=#{kuanhao} order by ${sortName} ${sortOrder}")
    List<Map> selectByKuanhao2(@Param("kuanhao") String kuanhao,@Param("sortName") String sortName, @Param("sortOrder") String sortOrder);

    @Select("select id,kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,hdbshuliang from houdaobu where kuanhao=#{kuanhao} and yanse=#{yanse}")
    Houdaobu selectKuanhaoYanse(String kuanhao,String yanse);

    @Select("select id,kuanhao,sum(cjbshuliang) as cjbshuliang from caijianbu where kuanhao=#{kuanhao}")
    List<Map> selectByYewubu(String kuanhao);

    @Update("update houdaobu_t set kuanhao=#{kuanhao},yanse=#{yanse},hdriqi=#{hdriqi},xs=#{xs},s=#{s},m=#{m},l=#{l},xl=#{xl},xxl=#{xxl},xxxl=#{xxxl} where id=#{id}")
    boolean updateHoudaobut(Houdaobut houdaobut);

    @Select("select * from houdaobu_t where id=#{id}")
    Houdaobut selectHoudaobutById(int id);

    @Update("update houdaobu set kuanhao=#{kuanhao},yanse=#{yanse},xs=#{xs},s=#{s},m=#{m},l=#{l},xl=#{xl},xxl=#{xxl},xxxl=#{xxxl},hdbshuliang=#{hdbshuliang} where kuanhao=#{kuanhao} and yanse=#{yanse}")
    boolean updateHoudaobu2(Houdaobu houdaobu);

    //求后道部数量的总和
    @Select("select sum(hdbshuliang) from houdaobu")
    List<Map> selectHdbZonghe();

    //根据款号求后道部数量的总和
    @Select("select sum(hdbshuliang) from houdaobu where kuanhao=#{kuanhao}")
    List<Map> selectHdbtZonghe(String kuanhao);

}