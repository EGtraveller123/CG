package com.ckgl.cg.dao;


import com.ckgl.cg.bean.Yewubu;
import com.ckgl.cg.bean.Yewubut;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface YewubuMapper {

    @Insert("insert into yewubu(kuanhao) values(#{kuanhao})")
    boolean insertYewubu(Yewubu yewubu);

}
