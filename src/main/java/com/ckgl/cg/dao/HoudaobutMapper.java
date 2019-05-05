package com.ckgl.cg.dao;

import com.ckgl.cg.bean.Houdaobut;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


import java.util.List;
@Component
public interface HoudaobutMapper {
    /**
     * 添加houdaobut信息
     * @param houdaobut
     */
    @Insert("insert into houdaobu_t(kuanhao,yanse,hdriqi,xs,s,m,l,xl,xxl,xxxl,beizhu) values (#{kuanhao},#{yanse},#{hdriqi},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{beizhu})")
    boolean insert(Houdaobut houdaobut);

    /**
     * 选择指定 款号
     * @param kuanhao
     * @return 返回指定对应的所有信息
     */
    @Select("select kuanhao,yanse,hdriqi,xs,s,m,l,xl,xxl,xxxl,beizhu from houdaobu_t where kuanhao=#{kuanhao}")
    Houdaobut selectByKuanhao(String kuanhao);

    @Select("select kuanhao,yanse,hdriqi,xs,s,m,l,xl,xxl,xxxl,beizhu from houdaobu_t where kuanhao=#{kuanhao}")
    List<Houdaobut> findByKuanhao(String kuanhao);
}