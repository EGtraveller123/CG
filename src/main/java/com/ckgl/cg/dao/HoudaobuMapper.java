package com.ckgl.cg.dao;


import com.ckgl.cg.bean.Houdaobu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public interface HoudaobuMapper {

    @Select("select * from houdaobu")
    List<Map> selectAll();

    @Select("select * from houdaobu where kuanhao=#{kuanhao}")
    List<Map> selectByKuanhao2(String kuanhao);

    @Select("select * from caijianbu where caijianbuid=#{caijianbuid}")
    Houdaobu selectHoudaobu(int yewubuid);

    @Update("update houdaobu set xs=#{xs},s=#{s},#{m},#{l},#{xl},#{xxl},#{xxxl} where caijianbuod=#{caijianbuid}")
    boolean updateHoudaobu(Houdaobu houdaobu);

    @Insert("insert into houdaobu(caijianbuid,kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl) values (#{caijianbuid},#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insertHoudaobu(Houdaobu houdaobu);

    @Select("select id,kuanhao,cjbshuliang from caijianbu where kuanhao=#{kuanhao}")
    List<Map> selectByCaijianbu(String kuanhao);
}