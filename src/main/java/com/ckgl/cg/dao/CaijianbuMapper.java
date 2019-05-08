package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Caijianbu;
import com.ckgl.cg.bean.Caijianbut;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Component
public interface CaijianbuMapper {

    /**
     * @param kuanhao
     * @return
     */
//    @Select("select kuanhao from caijianbu where kuanhao=#{kuanhao}")
    @Select("select * from caijianbu where kuanhao=#{kuanhao}")
    Caijianbu selectByKuanhao(String kuanhao);

    @Delete("delete from caijianbu where kuanhao=#{kuanhao}")
    boolean delete(String kuanhao);

    @Select("select a.ywbshuliang,b.* from yewubu a,caijianbu b where a.kuanhao=b.kuanhao")
    List<Caijianbu> selectAll();

    @Insert("insert into caijianbu(kuanhao,cjbshuliang) values (#{kuanhao},(select sum(xs+s+m+l+xl+xxl+xxxl) from caijianbu_t where kuanhao=#{kuanhao}))")
    boolean insert(Caijianbu caijianbu);

    @Update("update caijianbu set cjbshuliang = (select sum(xs+s+m+l+xl+xxl+xxxl) from caijianbu_t where kuanhao = #{kuanhao}) where kuanhao = #{kuanhao}")
    boolean update(Caijianbu caijianbu);



}