package com.ckgl.cg.dao;


import com.ckgl.cg.bean.Caijianbu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public interface CaijianbuMapper {

    @Select("select a.id,a.kuanhao,a.yanse,a.ywbshuliang,b.cjbshuliang as cjzonghe from yewubu a LEFT JOIN caijianbu b on a.id=b.yewubuid order by ${sortName} ${sortOrder}")
    List<Map> selectAll(@Param("sortName") String sortName, @Param("sortOrder") String sortOrder);

    @Insert("insert into caijianbu(yewubuid,kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,cjbshuliang) values (#{yewubuid},#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{cjbshuliang})")
    boolean insertcaijianbu(Caijianbu caijianbu);

    @Select("select * from caijianbu where yewubuid=#{id}")
    Caijianbu selectByid(Integer id);

    @Update("update caijianbu set xs=#{xs},s=#{s},m=#{m},l=#{l},xl=#{xl},xxl=#{xxl},xxxl=#{xxxl},cjbshuliang=#{cjbshuliang} where yewubuid=#{yewubuid}")
    boolean updateCaijianbu(Caijianbu caijianbu);

    @Select("select a.id,a.kuanhao,a.yanse,a.ywbshuliang,b.cjbshuliang as cjzonghe from yewubu a LEFT JOIN caijianbu b on a.id=b.yewubuid where a.kuanhao=#{kuanhao} order by ${sortOrder} ${sortName} ")
    List<Map> selectByKuanhao2(@Param("kuanhao") String kuanhao,@Param("sortName") String sortName, @Param("sortOrder") String sortOrder);

    @Select("select kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl from caijianbu where kuanhao=#{kuanhao} and yanse=#{yanse}")
    Caijianbu selectKuanhaoYanse(String kuanhao,String yanse);

    @Select("select id,kuanhao,sum(ywbshuliang) as ywbshuliang from yewubu where kuanhao=#{kuanhao}")
    List<Map> selectByYewubu(String kuanhao);
}