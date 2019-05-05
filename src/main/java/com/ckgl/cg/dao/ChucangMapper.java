package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Chucang;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ChucangMapper {

    /**
     * @param kuanhao
     * @return
     */
    @Select("select * from chucang where kuanhao=#{kuanhao}")
    Chucang selectByKuanhao(String kuanhao);

    @Select("select * from chucang")
    List<Chucang> selectAll();

    @Insert("insert into chucang(kuanhao,ccshuliang) values (#{kuanhao},(select sum(xs+s+m+l+xl+xxl+xxxl) from chucang_t where kuanhao=#{kuanhao}))")
    boolean insert(Chucang chucang);

    @Update("update chucang set ccshuliang = (select sum(xs+s+m+l+xl+xxl+xxxl) from chucang_t where kuanhao = #{kuanhao}) where kuanhao = #{kuanhao}")
    boolean update(Chucang chucang);

}
