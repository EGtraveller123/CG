package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Kucunt;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface KucuntMapper {

    @Update("update kucun_t set xs=#{xs}, s=#{s}, m=#{m}, l=#{l}, xl=#{xl}, xxl=#{xxl}, xxxl=#{xxxl} where id = #{id}")
    boolean updateKucunt(Kucunt kucunt);

    @Insert("insert into kucun_t (kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl) values (#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insertKucunt(Kucunt kucunt);

    @Delete("delete from kucun_t where id = #{id}")
    boolean deleteKucunt(int id);

    @Select("select sum(xs,s,m,l,xl,xxl,xxxl) from kucun_t where id=#{id}")
    int findKucuntSum(int id);

    @Select("select * from kucun_t")
    List<Kucunt> selectAll();

    @Select("select * from kucun_t where kuanhao=#{kuanhao}")
    List<Kucunt> selectByKuanhao(String kuanhao);

    @Select("select * from kucun_t where kuanhao=#{kuanhao} and yanse=#{yanse}")
    Kucunt selectByKuanhaoYanse(String kuanhao,String yanse);



}
