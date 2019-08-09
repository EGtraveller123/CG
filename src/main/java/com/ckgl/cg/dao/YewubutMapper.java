package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Yewubut;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface YewubutMapper {

    @Insert("insert into yewubu_t(kuanhao,kehu,yanse,mianliao,xxs,xs,s,m,l,xl,xxl,xxxl,chriqi,ywshuliang) " +
            "values (#{kuanhao},#{kehu},#{yanse},#{mianliao},#{xxs},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{chriqi},#{ywshuliang})")
    boolean insertYewubut(Yewubut yewubut);

    @Select("select id,kuanhao,kehu,yanse,xxs,xs,s,m,l,xl,xxl,xxxl,chriqi,ywshuliang from yewubu_t where kehu=#{kehu} order by ${sortName} ${sortOrder}")
    List<Map> selectByKehu(@Param("kehu") String kehu,@Param("sortName") String sortName,@Param("sortOrder") String sortOrder);

    @Select("select id,kuanhao,kehu,yanse,xxs,xs,s,m,l,xl,xxl,xxxl,chriqi,ywshuliang from yewubu_t where kuanhao=#{kuanhao} order by ${sortName} ${sortOrder}")
    List<Map> selectByKuanhao(@Param("kuanhao") String kuanhaom,@Param("sortName") String sortName,@Param("sortOrder") String sortOrder);

    @Select("select * from yewubu_t order by ${sortName} ${sortOrder}")
    List<Map> selectAll(@Param("sortName") String sortName,@Param("sortOrder") String sortOrder);

    @Delete("delete from yewubu_t where id=#{id}")
    boolean deleteYewubut(Integer id);

    @Select("select * from yewubu_t where id=#{id}")
    Yewubut selectByid(Integer id);

}
