package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Houdaobu;
import com.ckgl.cg.bean.Houdaobut;
import com.ckgl.cg.dao.CaijianbuMapper;
import com.ckgl.cg.dao.HoudaobuMapper;
import com.ckgl.cg.dao.HoudaobutMapper;
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
public class HoudaobutService {
    @Autowired
    private HoudaobutMapper houdaobutMapper;

    @Autowired
    private HoudaobuMapper houdaobuMapper;

    @Autowired
    private CaijianbuMapper caijianbuMapper;

    public Map<String, Object> selectByKuanhaoYanse(int offset, int limit, String kuanhao,String yanse) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> houdaobuts = null;
        long total = 0;
        boolean isPagination = true;
        if (offset < 0 || limit < 0)
            isPagination = false;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                houdaobuts = houdaobutMapper.selectByKuanhaoYanse(kuanhao,yanse);
                if (houdaobuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(houdaobuts);
                    total = pageInfo.getTotal();
                } else
                    houdaobuts = new ArrayList<>();
            } else {
                houdaobuts = houdaobutMapper.selectByKuanhaoYanse(kuanhao,yanse);
                if (houdaobuts != null)
                    total = houdaobuts.size();
                else
                    houdaobuts = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        resultSet.put("data", houdaobuts);
        resultSet.put("total", total);
        return resultSet;
    }

    public JSONObject insert(JSONObject jsonObject){
        Houdaobut houdaobut = new Houdaobut();
        Houdaobu houdaobu = new Houdaobu();
        Houdaobu houdaobu1 = new Houdaobu();
        JSONObject res = new JSONObject();
        houdaobu1=houdaobuMapper.selectByid(jsonObject.getInteger("id"));
        houdaobut.setKuanhao(jsonObject.getString("kuanhao"));
        houdaobut.setYanse(jsonObject.getString("yanse"));
        houdaobut.setXs(jsonObject.getInteger("xs"));
        houdaobut.setS(jsonObject.getInteger("s"));
        houdaobut.setM(jsonObject.getInteger("m"));
        houdaobut.setL(jsonObject.getInteger("l"));
        houdaobut.setXl(jsonObject.getInteger("xl"));
        houdaobut.setXxl(jsonObject.getInteger("xxl"));
        houdaobut.setXxxl(jsonObject.getInteger("xxxl"));
        houdaobut.setHdriqi(jsonObject.getString("hdriqi"));
        houdaobut.setBeizhu(jsonObject.getString("beizhu"));
        if(houdaobutMapper.insertHoudaobut(houdaobut)){
            res.put("result","success");
        }else {
            res.put("result","error");
            return res;
        }
        if(houdaobu1!=null){
            houdaobu1.setXs(houdaobu1.getXs()+jsonObject.getInteger("xs"));
            houdaobu1.setS(houdaobu1.getS()+jsonObject.getInteger("s"));
            houdaobu1.setM(houdaobu1.getM()+jsonObject.getInteger("m"));
            houdaobu1.setL(houdaobu1.getL()+jsonObject.getInteger("l"));
            houdaobu1.setXl(houdaobu1.getXl()+jsonObject.getInteger("xl"));
            houdaobu1.setXxl(houdaobu1.getXxl()+jsonObject.getInteger("xxl"));
            houdaobu1.setXxxl(houdaobu1.getXxxl()+jsonObject.getInteger("xxxl"));
            houdaobuMapper.updateHoudaobu(houdaobu);
            res.put("result","success");
        }else {
            houdaobu.setKuanhao(jsonObject.getString("kuanhao"));
            houdaobu.setYanse(jsonObject.getString("yanse"));
            houdaobu.setCaijianbuid(jsonObject.getInteger("id"));
            houdaobu.setXs(jsonObject.getInteger("xs"));
            houdaobu.setS(jsonObject.getInteger("s"));
            houdaobu.setM(jsonObject.getInteger("m"));
            houdaobu.setL(jsonObject.getInteger("l"));
            houdaobu.setXl(jsonObject.getInteger("xl"));
            houdaobu.setXxl(jsonObject.getInteger("xxl"));
            houdaobu.setXxxl(jsonObject.getInteger("xxxl"));
            houdaobuMapper.insertHoudaobu(houdaobu);
            res.put("result","success");
        }
        return res;
    }

    public Map<String, Object> findById(int offset, int limit,String id) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> houdaobuts = null;
        long total = 0;
        boolean isPagination = true;
        if (offset < 0 || limit < 0)
            isPagination = false;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                houdaobuts = houdaobutMapper.findById(Integer.valueOf(id));
                if (houdaobuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(houdaobuts);
                    total = pageInfo.getTotal();
                } else
                    houdaobuts = new ArrayList<>();
            } else {
                houdaobuts = houdaobutMapper.findById(Integer.valueOf(id));
                if (houdaobuts != null)
                    total = houdaobuts.size();
                else
                    houdaobuts = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        resultSet.put("data", houdaobuts);
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
                caijianbuts = houdaobutMapper.selectAll();
                if (caijianbuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(caijianbuts);
                    total = pageInfo.getTotal();
                } else
                    caijianbuts = new ArrayList<>();
            } else {
                caijianbuts = houdaobutMapper.selectAll();
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
