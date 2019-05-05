package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Jincang;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface JincangMapper {

    /**
     * @param kuanhao
     * @return
     */
    @Select("select * from jincang where kuanhao = #{kuanhao}")
    Jincang selectByKuanhao(String kuanhao);

    @Select("select * from jincang")
    List<Jincang> selectAll();

    @Insert("insert into jincang(kuanhao,jcshuliang) values (#{kuanhao},(select sum(xs+s+m+l+xl+xxl+xxxl) from jincang_t where kuanhao=#{kuanhao}))")
    boolean insert(Jincang jincang);

    @Update("update jincang set jcshuliang = (select sum(xs+s+m+l+xl+xxl+xxxl) from jincang_t where kuanhao = #{kuanhao}) where kuanhao = #{kuanhao}")
    boolean update(Jincang jincang);

}
