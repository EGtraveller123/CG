package com.ckgl.cg.dao;


import com.ckgl.cg.bean.Yewubu;
import com.ckgl.cg.bean.Yewubut;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface YewubuMapper {

    /**
     * 选择所有的 Yewubu
     * @return 返回所有的 Yewubu
     */
    @Select("select * from yewubu")
    List<Yewubu> selectAll();

    @Update("update yewubu set ywbshuliang=#{ywbshuliang} where kuanhao=#{kuanhao}")
    List<Yewubu> updateshuliang(Yewubu yewubu);

    /**
     * @return 返回指定KUANHAO对应的Yewubu信息
     */
    @Select("select * from yewubu where kuanhao = #{kuanhao}")
    Yewubu selectByKuanhao(String kuanhao);


    /**
     * 选择指定 kehu
     * @param kehu 客户的名称
     * @return 返回指定CustomerName对应的Customer
     */
    @Select("select * from yewubu where kehu = #{kehu}")
    List<Yewubu> selectByKehu(String kehu);


    /**
     * 插入 Yewubu 到数据库中
     * 不需要指定主键，采用的数据库 AI 方式
     * @param yewubu Yewubu 实例
     */
    @Insert("insert into yewubu(kuanhao,kehu,ywbshuliang,mianliao,chriqi) values (#{kuanhao},#{kehu},#{ywbshuliang},#{mianliao},#{chriqi})")
    boolean insert(Yewubu yewubu);




    /**
     * 更新 Yewubu 到数据库
     * 该 Yewubu 必须已经存在于数据库中，即已经分配主键，否则将更新失败
     * @param yewubu Yewubu 实例
     */
    @Update("update yewubu set kehu=#{kehu}, ywbshuliang=#{ywbshuliang}, mianliao=#{mianliao}, chriqi=#{chriqi} where kuanhao=#{kuanhao}")
    boolean update(Yewubu yewubu);

    @Delete("delete from yewubu_t where kuanhao=#{kuanhao}")
    boolean deleteYewubut(String kuanhao);

    /**
     * 根据款号删除客户信息
     *
     * @param kuanhao 客户ID
     */
    @Delete("delete from yewubu where kuanhao=#{kuanhao}")
    boolean delete(String kuanhao);

    /**
     * 插入 Yewubu 到数据库中
     * 不需要指定主键，采用的数据库 AI 方式
     * @param yewubut Yewubut 实例
     */
    @Insert("insert into yewubu_t(kuanhao,yanse,xs,s,m,l,xl,xxl,xxxl) values (#{kuanhao},#{yanse},#{xs},#{s},#{m},#{l},#{xl},#{xxl},#{xxxl})")
    boolean insertYewu(Yewubut yewubut);

    @Update("update yewubu_t set kuanhao=#{kuanhao},yanse=#{yanse},xs=#{xs},s=#{s},m=#{m},l=#{l},xl=#{xl},xxl=#{xxl},xxxl=#{xxxl} where kuanhao=#{kuanhao}")
    boolean updateYewu(Yewubut yewubut);

    @Update("update yewubu_t set kuanhao=#{kuanhao},yanse=#{yanse},xs=#{xs},s=#{s},m=#{m},l=#{l},xl=#{xl},xxl=#{xxl},xxxl=#{xxxl} where kuanhao=#{kuanhao} and yanse=#{yanse}")
    boolean updateYewu2(Yewubut yewubut);
}
