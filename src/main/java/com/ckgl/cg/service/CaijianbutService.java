package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Caijianbu;
import com.ckgl.cg.bean.Caijianbut;
import com.ckgl.cg.bean.Yewubu;
import com.ckgl.cg.bean.Yewubut;
import com.ckgl.cg.dao.CaijianbuMapper;
import com.ckgl.cg.dao.CaijianbutMapper;
import com.ckgl.cg.dao.HoudaobuMapper;
import com.ckgl.cg.dao.YewubuMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CaijianbutService {
    @Autowired
    private CaijianbutMapper caijianbutMapper;

    @Autowired
    private CaijianbuMapper caijianbuMapper;

    @Autowired
    private YewubuMapper yewubuMapper;

    public Map<String, Object> selectByKuanhao(int offset, int limit, String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> caijianbuts = null;
        long total = 0;
        boolean isPagination = true;
        if (offset < 0 || limit < 0)
            isPagination = false;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                caijianbuts = caijianbutMapper.selectByKuanhao(kuanhao);
                if (caijianbuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(caijianbuts);
                    total = pageInfo.getTotal();
                } else
                    caijianbuts = new ArrayList<>();
            } else {
                caijianbuts = caijianbutMapper.selectByKuanhao(kuanhao);
                if (caijianbuts != null)
                    total = caijianbuts.size();
                else
                    caijianbuts = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        resultSet.put("data", caijianbuts);
        resultSet.put("total", total);
        return resultSet;
    }


    public JSONObject insert(JSONObject jsonObject) {
        Caijianbut caijianbut = new Caijianbut();
        Caijianbu caijianbu = new Caijianbu();
        Caijianbu caijianbu1 = new Caijianbu();
        Caijianbut caijianbut1 = new Caijianbut();
        JSONObject res = new JSONObject();
        caijianbu1=caijianbuMapper.selectYewubuid(jsonObject.getInteger("yewubuid"));
        caijianbut.setKuanhao(jsonObject.getString("kuanhao"));
        caijianbut.setYanse(jsonObject.getString("yanse"));
        caijianbut.setXs(jsonObject.getInteger("xs"));
        caijianbut.setS(jsonObject.getInteger("s"));
        caijianbut.setM(jsonObject.getInteger("m"));
        caijianbut.setL(jsonObject.getInteger("l"));
        caijianbut.setXl(jsonObject.getInteger("xl"));
        caijianbut.setXxl(jsonObject.getInteger("xxl"));
        caijianbut.setXxxl(jsonObject.getInteger("xxxl"));
        caijianbut.setCjriqi(jsonObject.getString("cjriqi"));
        caijianbut1=caijianbutMapper.selectByKuanhaoRiqiYanse(jsonObject.getString("kuanhao"),jsonObject.getString("cjriqi"),jsonObject.getString("yanse"));
        if(caijianbut1==null){
            caijianbutMapper.insertCaijiant(caijianbut);
            res.put("result","success");
        }else{
            caijianbut.setXs(caijianbut1.getXs()+jsonObject.getInteger("xs"));
            caijianbut.setS(caijianbut1.getS()+jsonObject.getInteger("s"));
            caijianbut.setM(caijianbut1.getM()+jsonObject.getInteger("m"));
            caijianbut.setL(caijianbut1.getL()+jsonObject.getInteger("l"));
            caijianbut.setXl(caijianbut1.getXl()+jsonObject.getInteger("xl"));
            caijianbut.setXxl(caijianbut1.getXxl()+jsonObject.getInteger("xxl"));
            caijianbut.setXxxl(caijianbut1.getXxxl()+jsonObject.getInteger("xxxl"));
            caijianbutMapper.updateCaijianbut(caijianbut);
            res.put("result","success");
        }
        if(caijianbu1!=null){
            caijianbu.setXs(caijianbu1.getXs()+jsonObject.getInteger("xs"));
            caijianbu.setS(caijianbu.getS()+jsonObject.getInteger("s"));
            caijianbu.setM(caijianbu1.getM()+jsonObject.getInteger("m"));
            caijianbu.setL(caijianbu1.getL()+jsonObject.getInteger("l"));
            caijianbu.setXl(caijianbu1.getXl()+jsonObject.getInteger("xl"));
            caijianbu.setXxl(caijianbu1.getXxl()+jsonObject.getInteger("xxl"));
            caijianbu.setXxxl(caijianbu1.getXxxl()+jsonObject.getInteger("xxxl"));
            caijianbu.setCjbshuliang(caijianbu.getCjbshuliang()+jsonObject.getInteger("xs")+jsonObject.getInteger("s")+jsonObject.getInteger("m")+jsonObject.getInteger("l")+jsonObject.getInteger("xl")+jsonObject.getInteger("xxl")+jsonObject.getInteger("xxxl"));
            caijianbuMapper.updateCaijianbu(caijianbu);
            res.put("result","success");
        }else{
            caijianbu.setKuanhao(jsonObject.getString("kuanhao"));
            caijianbu.setYanse(jsonObject.getString("yanse"));
            caijianbu.setYewubuid(yewubuMapper.selectByKuanhaoYanse(caijianbu.getKuanhao(),caijianbu.getYanse()).getId());
            caijianbu.setXs(jsonObject.getInteger("xs"));
            caijianbu.setS(jsonObject.getInteger("s"));
            caijianbu.setM(jsonObject.getInteger("m"));
            caijianbu.setL(jsonObject.getInteger("l"));
            caijianbu.setXl(jsonObject.getInteger("xl"));
            caijianbu.setXxl(jsonObject.getInteger("xxl"));
            caijianbu.setXxxl(jsonObject.getInteger("xxxl"));
            caijianbu.setCjbshuliang(jsonObject.getInteger("xs")+jsonObject.getInteger("s")+jsonObject.getInteger("m")+jsonObject.getInteger("l")+jsonObject.getInteger("xl")+jsonObject.getInteger("xxl")+jsonObject.getInteger("xxxl"));
            caijianbuMapper.insertcaijianbu(caijianbu);
            res.put("result","success");
        }
        return res;
    }


    public Map<String, Object> findByKuanhao(int offset, int limit, String id) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> caijianbuts = null;
        long total = 0;
        boolean isPagination = true;
        if (offset < 0 || limit < 0)
            isPagination = false;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                caijianbuts = caijianbutMapper.findById(Integer.valueOf(id));
                if (caijianbuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(caijianbuts);
                    total = pageInfo.getTotal();
                } else
                    caijianbuts = new ArrayList<>();
            } else {
                caijianbuts = caijianbutMapper.findById(Integer.valueOf(id));
                if (caijianbuts != null)
                    total = caijianbuts.size();
                else
                    caijianbuts = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        resultSet.put("data", caijianbuts);
        resultSet.put("total", total);
        return resultSet;
    }

    public Map<String, Object> selectAll(int offset, int limit) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> caijianbuts = null;
        long total = 0;
        boolean isPagination = true;
        if (offset < 0 || limit < 0)
            isPagination = false;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                caijianbuts = caijianbutMapper.selectAll();
                if (caijianbuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(caijianbuts);
                    total = pageInfo.getTotal();
                } else
                    caijianbuts = new ArrayList<>();
            } else {
                caijianbuts = caijianbutMapper.selectAll();
                if (caijianbuts != null)
                    total = caijianbuts.size();
                else
                    caijianbuts = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        resultSet.put("data", caijianbuts);
        resultSet.put("total", total);
        return resultSet;
    }

}

