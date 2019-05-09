package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Yewubut;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface YewubutMapper {

    @Insert("insert into yewubu_t(kuanhao,kehu,yanse,xs,s,m,l,xl,xxl,xxxl,chriqi,ywshuliang) " +
            "values (#{kuanhao},#{kehu},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{chriqi},#{ywshuliang})")
    boolean insertYewubut(Yewubut yewubut);

    @Select("select kuanhao,kehu,yanse,xs,s,m,l,xl,xxl,xxxl,chriqi,cjshuliang where kehu=#{kehu}")
    List<Map> selectByKehu(String  kehu);

    @Select("select kuanhao,kehu,yanse,xs,s,m,l,xl,xxl,xxxl,chriqi,cjshuliang from yewubu_t where kuanhao=#{kuanhao}")
    List<Map> selectByKuanhao(String kuanhao);

    @Select("select * from yewubu_t")
    List<Map> selectAll();


}
