package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Houdaobu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface HoudaobuMapper {

    /**
     * @param kuanhao
     * @return
     */
    @Select("select * from houdaobu where kuanhao=#{kuanhao}")
    Houdaobu selectByKuanhao(String kuanhao);

    @Select("select a.ywbshuliang,b.cjbshuliang,c.* from yewubu a,caijianbu b,houdaobu c where a.kuanhao=b.kuanhao and b.kuanhao=c.kuanhao")
    List<Houdaobu> selectAll();

    @Insert("insert into houdaobu(kuanhao,hdbshuliang) values (#{kuanhao},(select sum(xs+s+m+l+xl+xxl+xxxl) from houdaobu_t where kuanhao=#{kuanhao}))")
    boolean insert(Houdaobu houdaobu);

    @Update("update houdaobu set hdbshuliang = (select sum(xs+s+m+l+xl+xxl+xxxl) from houdaobu_t where kuanhao = #{kuanhao}) where kuanhao = #{kuanhao}")
    boolean update(Houdaobu houdaobu);

    @Delete("delete from houdaobu where kuanhao=#{kuanhao}")
    boolean delete(String kuanhao);

}