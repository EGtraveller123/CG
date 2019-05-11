package com.ckgl.cg.dao;
import com.ckgl.cg.bean.Caijianbut;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public interface CaijianbutMapper {

    @Select("select * from caijianbu_t")
    List<Map> selectAll();

    @Select("select kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,cjriqi from caijianbu_t where kuanhao=#{kuanhao} and yanse=#{yanse}")
    List<Map> selectByKuanhaoYanse(@Param("kuanhao")String kuanhao,@Param("yanse") String yanse);

    @Select("select * from yewubu a left join caijianbu b on a.id=b.yewubuid where b.yewubuid=#{id}")
    List<Map> findById(Integer id);

    @Insert("insert into caijianbu_t(kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,cjriqi) " +
            "values(#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{cjriqi})")
    boolean insertCaijiant(Caijianbut caijianbut);


}