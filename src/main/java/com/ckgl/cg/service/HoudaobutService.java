package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Caijianbu;
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

    public Map<String, Object> selectById(int offset, int limit, int id) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        Caijianbu caijianbus = null;
        List<Map> houdaobuts1 = null;
        long total = 0;
        boolean isPagination = true;
        if (offset < 0 || limit < 0)
            isPagination = false;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                caijianbus = houdaobutMapper.selectById(id);
                houdaobuts1 = houdaobutMapper.selectByKuanhaoYanse(caijianbus.getKuanhao(),caijianbus.getYanse());
                if (houdaobuts1 != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(houdaobuts1);
                    total = pageInfo.getTotal();
                } else
                    houdaobuts1 = new ArrayList<>();
            } else {
                caijianbus = houdaobutMapper.selectById(id);
                houdaobuts1 = houdaobutMapper.selectByKuanhaoYanse(caijianbus.getKuanhao(),caijianbus.getYanse());
                if (houdaobuts1 != null)
                    total = houdaobuts1.size();
                else
                    houdaobuts1 = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        resultSet.put("data", houdaobuts1);
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
        houdaobut.setXs(Integer.valueOf(jsonObject.getString("xs")));
        houdaobut.setS(Integer.valueOf(jsonObject.getString("s")));
        houdaobut.setM(Integer.valueOf(jsonObject.getString("m")));
        houdaobut.setL(Integer.valueOf(jsonObject.getString("l")));
        houdaobut.setXl(Integer.valueOf(jsonObject.getString("xl")));
        houdaobut.setXxl(Integer.valueOf(jsonObject.getString("xxl")));
        houdaobut.setXxxl(Integer.valueOf(jsonObject.getString("xxxl")));
        houdaobut.setHdriqi(jsonObject.getString("hdriqi"));
        houdaobut.setBeizhu(jsonObject.getString("beizhu"));
        if(houdaobutMapper.insertHoudaobut(houdaobut)){
            res.put("result","success");
        }else {
            res.put("result","error");
            return res;
        }
        if(houdaobu1!=null){
            houdaobu1.setXs(houdaobu1.getXs()+Integer.valueOf(jsonObject.getString("xs")));
            houdaobu1.setS(houdaobu1.getS()+Integer.valueOf(jsonObject.getString("s")));
            houdaobu1.setM(houdaobu1.getM()+Integer.valueOf(jsonObject.getString("m")));
            houdaobu1.setL(houdaobu1.getL()+Integer.valueOf(jsonObject.getString("l")));
            houdaobu1.setXl(houdaobu1.getXl()+Integer.valueOf(jsonObject.getString("xl")));
            houdaobu1.setXxl(houdaobu1.getXxl()+Integer.valueOf(jsonObject.getString("xxl")));
            houdaobu1.setXxxl(houdaobu1.getXxxl()+Integer.valueOf(jsonObject.getString("xxxl")));
            houdaobuMapper.updateHoudaobu(houdaobu1);
            res.put("result","success");
        }else {
            houdaobu.setKuanhao(jsonObject.getString("kuanhao"));
            houdaobu.setYanse(jsonObject.getString("yanse"));
            houdaobu.setCaijianbuid(jsonObject.getInteger("id"));
            houdaobu.setXs(Integer.valueOf(jsonObject.getString("xs")));
            houdaobu.setS(Integer.valueOf(jsonObject.getString("s")));
            houdaobu.setM(Integer.valueOf(jsonObject.getString("m")));
            houdaobu.setL(Integer.valueOf(jsonObject.getString("l")));
            houdaobu.setXl(Integer.valueOf(jsonObject.getString("xl")));
            houdaobu.setXxl(Integer.valueOf(jsonObject.getString("xxl")));
            houdaobu.setXxxl(Integer.valueOf(jsonObject.getString("xxxl")));
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
