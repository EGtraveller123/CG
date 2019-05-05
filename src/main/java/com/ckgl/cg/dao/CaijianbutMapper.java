package com.ckgl.cg.dao;
import com.ckgl.cg.bean.Caijianbut;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface CaijianbutMapper {
    /**
     * 添加caijianbut信息
     * @param caijianbut
     */
    @Insert("insert into caijianbu_t(kuanhao,yanse,cjriqi,xs,s,m,l,xl,xxl,xxxl) values (#{kuanhao},#{yanse},#{cjriqi},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insert(Caijianbut caijianbut);

    /**
     * 选择指定 款号
     * @param kuanhao
     * @return 返回指定对应的所有信息
     */
    @Select("select kuanhao,yanse,cjriqi,xs,s,m,l,xl,xxl,xxxl from caijianbu_t where kuanhao=#{kuanhao}")
    Caijianbut selectByKuanhao(String kuanhao);

    @Select("select kuanhao,yanse,cjriqi,xs,s,m,l,xl,xxl,xxxl from caijianbu_t where kuanhao=#{kuanhao}")
    List<Caijianbut> findByKuanhao(String kuanhao);

    @Select("select sum(xs),SUM(s),sum(m),SUM(l),SUM(xl),SUM(xxl),SUM(xxxl) from caijianbu_t where kuanhao=#{kuanhao} and yanse=#{yanse}")
    List<Caijianbut> selectA(String kuanhao);
}