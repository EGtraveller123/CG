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
        Yewubu yewubu1 = new Yewubu();
        JSONObject res = new JSONObject();
        yewubu1=yewubuMapper.selectByKuanhaoYanse(jsonObject.getString("kuanhao"),jsonObject.getString("yanse"));
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
        if(yewubu1==null){
            yewubu.setKuanhao(jsonObject.getString("kuanhao"));
            yewubu.setYanse(jsonObject.getString("yanse"));
            yewubu.setXs(jsonObject.getInteger("xs"));
            yewubu.setS(jsonObject.getInteger("s"));
            yewubu.setM(jsonObject.getInteger("m"));
            yewubu.setL(jsonObject.getInteger("l"));
            yewubu.setXl(jsonObject.getInteger("xl"));
            yewubu.setXxl(jsonObject.getInteger("xxl"));
            yewubu.setXxxl(jsonObject.getInteger("xxxl"));
            yewubu.setYwbshuliang(jsonObject.getInteger("xs")+jsonObject.getInteger("s")+jsonObject.getInteger("m")+jsonObject.getInteger("l")+jsonObject.getInteger("xl")+jsonObject.getInteger("xxl")+jsonObject.getInteger("xxxl"));
            yewubuMapper.insertYewubu(yewubu);
            res.put("result","success");
        }else{
            yewubu.setKuanhao(yewubu1.getKuanhao());
            yewubu.setYanse(yewubu1.getYanse());
            yewubu.setXs(yewubu1.getXs()+jsonObject.getInteger("xs"));
            yewubu.setS(yewubu1.getS()+jsonObject.getInteger("s"));
            yewubu.setM(yewubu1.getM()+jsonObject.getInteger("m"));
            yewubu.setL(yewubu1.getL()+jsonObject.getInteger("l"));
            yewubu.setXl(yewubu1.getXl()+jsonObject.getInteger("xl"));
            yewubu.setXxl(yewubu1.getXxl()+jsonObject.getInteger("xxl"));
            yewubu.setXxxl(yewubu1.getXxxl()+jsonObject.getInteger("xxxl"));
            yewubu.setYwbshuliang(yewubu1.getYwbshuliang()+jsonObject.getInteger("xs")+jsonObject.getInteger("s")+jsonObject.getInteger("m")+jsonObject.getInteger("l")+jsonObject.getInteger("xl")+jsonObject.getInteger("xxl")+jsonObject.getInteger("xxxl"));
            yewubuMapper.updateYewubu(yewubu);
            res.put("result","success");
        }
        return res;
    }

    public JSONObject deleteYewubut(String jsonObject){
        JSONObject res = new JSONObject();
        Yewubu yewubu = new Yewubu();
        Yewubut yewubut = new Yewubut();
        yewubut=yewubutMapper.selectByid(Integer.valueOf(jsonObject));
        yewubu=yewubuMapper.selectByKuanhaoYanse(yewubut.getKuanhao(),yewubut.getYanse());
        if(yewubutMapper.deleteYewubut(Integer.valueOf(jsonObject))){
            yewubu.setXs(yewubu.getXs()-yewubut.getXs());
            yewubu.setS(yewubu.getS()-yewubut.getS());
            yewubu.setM(yewubu.getM()-yewubut.getM());
            yewubu.setL(yewubu.getL()-yewubut.getL());
            yewubu.setXl(yewubu.getXl()-yewubut.getXl());
            yewubu.setXxl(yewubu.getXxl()-yewubut.getXxl());
            yewubu.setXxxl(yewubu.getXxxl()-yewubut.getXxxl());
            yewubu.setYwbshuliang(yewubu.getXs()+yewubu.getS()+yewubu.getM()+yewubu.getL()+yewubu.getXl()+yewubu.getXxl()+yewubu.getXxxl());
            yewubuMapper.updateYewubu(yewubu);
            res.put("result","success");
        }else{
            res.put("result","error");
        }
        return res;
    }

}
