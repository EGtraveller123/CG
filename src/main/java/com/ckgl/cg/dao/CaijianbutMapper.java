package com.ckgl.cg.dao;
import com.ckgl.cg.bean.Caijianbut;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Component
public interface CaijianbutMapper {

    @Select("select * from caijianbu_t")
    List<Map> selectAll();

    @Select("select kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,cjriqi from caijianbu_t where kuanhao=#{kuanhao}")
    List<Map> selectByKuanhao(String kuanhao);

    @Select("select kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl,cjriqi from caijianbu_t where cjriqi=#{cjriqi}")
    Caijianbut selectByKuanhaoRiqiYanse(String Kuanhao,String cjriqi,String yanse);

    @Select("select a.kuanhao as ye_kuanhao,a.yanse as ye_yanse,sum(a.xs) as ye_xs,sum(a.s) as ye_s,sum(a.m) as ye_m," +
            "sum(a.l) as ye_l,sum(a.xl) as ye_xl,sum(a.xxl) as ye_xxl,sum(a.xxxl) as ye_xxxl,sum(b.xs) as ca_xs,sum(b.s) as ca_s," +
            "sum(b.m) as ca_m,sum(b.l) as ca_l,sum(b.xl) as ca_xl,sum(b.xxl) as ca_xxl,sum(b.xxxl) as ca_xxxl " +
            "from yewubu_t a join caijianbu_t b on a.id=b.id where id=#{id}")
    List<Map> findById(Integer id);

    @Insert("insert into caijianbu_t(kuanhao,yanse,yewubutid,xs,s,m,l,xl,xxl,xxxl,cjriqi) " +
            "values(#{kuanhao},#{yanse},#{yewubutid},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl},#{cjriqi})")
    boolean insertCaijiant(Caijianbut caijianbut);

    @Update("update caijianbu_t set xs=#{xs},s=#{s},m=#{m},#{l},#{xl},#{xxl},#{xxxl} where kuanhao=#{kuanhao} and yanse=#{yanse} and cjriqi=#{cjriqi}")
    boolean updateCaijianbut(Caijianbut caijianbut);

}