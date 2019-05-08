package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Kucun;
import com.ckgl.cg.bean.Kucunt;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface KucunMapper {

    @Select("select * from kucun where kuanhao=#{kuanhao}")
    Kucun selectByKuanhao(String kuanhao);

    @Select("select * from kucun")
    List<Kucun> selectAll();

    @Insert("insert into kucun(kuanhao,kcshuliang) values(#{kuanhao},(select sum(xs+s+m+l+xl+xxl+xxxl) from kucun_t where kuanhao=#{kuanhao}))")
    boolean insertKucun(String kuanhao);

    @Update("update kucun set kcshuliang=(select sum(xs+s+m+l+xl+xxl+xxxl) from kucun_t where kuanhao=#{kuanhao}) where kuanhao=#{kuanhao}")
    boolean updateKucun(String kuanhao);

    @Delete("delete from kucun where kuanhao=#{kuanhao}")
    boolean deleteKucun(String kuanhao);



}
