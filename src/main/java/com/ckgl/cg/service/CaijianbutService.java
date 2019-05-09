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
        JSONObject res = new JSONObject();
        caijianbu1=caijianbuMapper.selectByKuanhao(jsonObject.getString("kuanhao"));
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
        caijianbut.setCjshuliang(jsonObject.getInteger("xs")+jsonObject.getInteger("s")+jsonObject.getInteger("m")+jsonObject.getInteger("l")+jsonObject.getInteger("xl")+jsonObject.getInteger("xxl")+jsonObject.getInteger("xxxl"));
        if(caijianbu1!=null){
            caijianbu.setCjbshuliang(caijianbu1.getCjbshuliang()+jsonObject.getInteger("xs")+jsonObject.getInteger("s")+jsonObject.getInteger("m")+jsonObject.getInteger("l")+jsonObject.getInteger("xl")+jsonObject.getInteger("xxl")+jsonObject.getInteger("xxxl"));
            caijianbuMapper.updateCaijianbu(caijianbu1.getKuanhao());
            caijianbutMapper.insertCaijiant(caijianbut);
            res.put("result","success");
        }else{
            caijianbu1.setKuanhao(jsonObject.getString("kuanhao"));
            caijianbu1.setCjbshuliang(jsonObject.getInteger("xs")+jsonObject.getInteger("s")+jsonObject.getInteger("m")+jsonObject.getInteger("l")+jsonObject.getInteger("xl")+jsonObject.getInteger("xxl")+jsonObject.getInteger("xxxl"));
            caijianbuMapper.insertCaijianbu(caijianbu1);
            caijianbutMapper.insertCaijiant(caijianbut);
            res.put("result","success");
        }
        return res;
    }


    public Map<String, Object> findByKuanhao(int offset, int limit, String kuanhao) {
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
                caijianbuts = caijianbutMapper.findByKuanhao(kuanhao);
                if (caijianbuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(caijianbuts);
                    total = pageInfo.getTotal();
                } else
                    caijianbuts = new ArrayList<>();
            } else {
                caijianbuts = caijianbutMapper.findByKuanhao(kuanhao);
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

