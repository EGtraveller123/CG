package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Yewubu;
import com.ckgl.cg.bean.Yewubut;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface YewubutMapper {
    /**
     * @param yewubut
     */
    //添加
    @Insert("insert into yewubu_t(kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl) values (#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insert(Yewubut yewubut);
    /**
     * @param kuanhao
     */
    //删除
    @Delete("delete from yewubu_t where kuanhao=#{kuanhao}")
    boolean delete(String kuanhao);
    /**
     * @param kuanhao
     */
    //查询
    @Select("select kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl from yewubu_t where kuanhao=#{kuanhao}")
//    @Select("select * from yewubut where kuanhao=#{kuanhao}")
    Yewubut selectByKuanhao(String kuanhao);

    @Select("select kuanhao,yanse from yewubu_t where kuanhao=#{kuanhao}")
//    @Select("select * from yewubut where kuanhao=#{kuanhao}")
    List<Yewubut> selectKuanhao(String kuanhao);

    @Select("select kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl from yewubu_t where kuanhao=#{kuanhao}")
    List<Yewubut> findByKuanhao(String kuanhao);


}
