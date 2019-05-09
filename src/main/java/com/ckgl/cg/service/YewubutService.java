package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Yewubu;
import com.ckgl.cg.bean.Yewubut;
import com.ckgl.cg.dao.YewubuMapper;
import com.ckgl.cg.dao.YewubutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class YewubutService {
    @Autowired
    private YewubutMapper yewubutMapper;

    @Autowired
    private YewubuMapper yewubuMapper;


    public JSONObject insert(JSONObject jsonObject){
        Yewubut yewubut = new Yewubut();
        Yewubu yewubu = new Yewubu();
        JSONObject res = new JSONObject();
        yewubut.setKuanhao(jsonObject.getString("kuanhao"));
        yewubut.setKehu(jsonObject.getString("kehu"));
        yewubut.setYanse(jsonObject.getString("yanse"));
        yewubut.setMianliao(jsonObject.getString("mianliao"));
        yewubut.setXs(jsonObject.getInteger("xs"));
        yewubut.setS(jsonObject.getInteger("s"));
        yewubut.setM(jsonObject.getInteger("m"));
        yewubut.setL(jsonObject.getInteger("l"));
        yewubut.setXl(jsonObject.getInteger("xl"));
        yewubut.setXxl(jsonObject.getInteger("xxl"));
        yewubut.setXxxl(jsonObject.getInteger("xxxl"));
        yewubut.setChriqi(jsonObject.getString("chriqi"));
        yewubut.setYwshuliang(jsonObject.getInteger("xs")+jsonObject.getInteger("s")+jsonObject.getInteger("m")+jsonObject.getInteger("l")+jsonObject.getInteger("xl")+jsonObject.getInteger("xxl")+jsonObject.getInteger("xxxl"));
        yewubutMapper.insertYewubut(yewubut);
        yewubu.setKuanhao(jsonObject.getString("kuanhao"));
        yewubu.setYewubutid(yewubut.getId());
        yewubuMapper.insertYewubu(yewubu);
        res.put("result","success");
        return res;
    }

}
