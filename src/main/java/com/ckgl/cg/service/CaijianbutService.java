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


    public Map<String, Object> selectById(int offset, int limit, int id) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> caijianbuts1 = null;
        Yewubu yewubus = null;
        long total = 0;
        boolean isPagination = true;
        Yewubu yewubus = caijianbutMapper.selectById(id);
        String ywbkh = yewubus.getKuanhao();
        String ywbys=yewubus.getYanse();
        if (offset < 0 || limit < 0)
            isPagination = false;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                caijianbuts1 = caijianbutMapper.selectByKuanhaoYanse(ywbkh,ywbys);
                if (caijianbuts1 != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(caijianbuts1);
                    total = pageInfo.getTotal();
                } else
                    caijianbuts1 = new ArrayList<>();
            } else {
                caijianbuts1 = caijianbutMapper.selectByKuanhaoYanse(ywbkh,ywbys);
                if (caijianbuts1 != null)
                    total = caijianbuts1.size();
                else
                    caijianbuts1 = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        resultSet.put("data", caijianbuts1);
        resultSet.put("total", total);
        return resultSet;
    }


    public JSONObject insert(JSONObject jsonObject) {
        Caijianbut caijianbut = new Caijianbut();
        Caijianbu caijianbu = new Caijianbu();
        Caijianbu caijianbu1 = new Caijianbu();
        JSONObject res = new JSONObject();
        caijianbu1=caijianbuMapper.selectByid(jsonObject.getInteger("id"));
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
        if(caijianbutMapper.insertCaijiant(caijianbut)){
            res.put("result","success");
        }else {
            res.put("result","error");
            return res;
        }
        if(caijianbu1==null){
            caijianbu.setKuanhao(jsonObject.getString("kuanhao"));
            caijianbu.setYanse(jsonObject.getString("yanse"));
            caijianbu.setYewubuid(jsonObject.getInteger("id"));
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
        }else{
            caijianbu1.setXs(caijianbu1.getXs()+jsonObject.getInteger("xs"));
            caijianbu1.setS(caijianbu1.getS()+jsonObject.getInteger("s"));
            caijianbu1.setM(caijianbu1.getM()+jsonObject.getInteger("m"));
            caijianbu1.setL(caijianbu1.getL()+jsonObject.getInteger("l"));
            caijianbu1.setXl(caijianbu1.getXl()+jsonObject.getInteger("xl"));
            caijianbu1.setXxl(caijianbu1.getXxl()+jsonObject.getInteger("xxl"));
            caijianbu1.setXxxl(caijianbu1.getXxxl()+jsonObject.getInteger("xxxl"));
            caijianbu1.setCjbshuliang(caijianbu1.getCjbshuliang()+jsonObject.getInteger("xs")+jsonObject.getInteger("s")+jsonObject.getInteger("m")+jsonObject.getInteger("l")+jsonObject.getInteger("xl")+jsonObject.getInteger("xxl")+jsonObject.getInteger("xxxl"));
            caijianbuMapper.updateCaijianbu(caijianbu1);
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

