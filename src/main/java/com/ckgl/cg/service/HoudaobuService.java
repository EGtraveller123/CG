package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Houdaobu;
import com.ckgl.cg.bean.Houdaobut;
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
public class HoudaobuService {
    @Autowired
    private HoudaobuMapper houdaobuMapper;

    @Autowired
    private HoudaobutMapper houdaobutMapper;


    public Map<String, Object> selectByKuanhao(int offset, int limit, String kuanhao,String sortName,String sortOrder) {
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
                houdaobuts = houdaobuMapper.selectByKuanhao2(kuanhao,sortName,sortOrder);
                if (houdaobuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(houdaobuts);
                    total = pageInfo.getTotal();
                } else
                    houdaobuts = new ArrayList<>();
            } else {
                houdaobuts = houdaobuMapper.selectByKuanhao2(kuanhao,sortName,sortOrder);
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

    public Map<String, Object> selectAll(int offset, int limit,String sortName,String sortOrder) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> houdaobus = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                houdaobus = houdaobuMapper.selectAll(sortName, sortOrder);
                if (houdaobus != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(houdaobus);
                    total = pageInfo.getTotal();
                } else
                    houdaobus = new ArrayList<>();
            } else {
                houdaobus = houdaobuMapper.selectAll(sortName, sortOrder);
                if (houdaobus != null)
                    total = houdaobus.size();
                else
                    houdaobus = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", houdaobus);
        resultSet.put("total", total);
        return resultSet;
    }

    public Map<String, Object> selectByCaijianbu(int offset, int limit,String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> Houdaobus = null;
        long total = 0;
        boolean isPagination = true;
        if (offset < 0 || limit < 0)
            isPagination = false;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                Houdaobus = houdaobuMapper.selectByYewubu(kuanhao);
                if (Houdaobus != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(Houdaobus);
                    total = pageInfo.getTotal();
                } else
                    Houdaobus = new ArrayList<>();
            } else {
                Houdaobus = houdaobuMapper.selectByYewubu(kuanhao);
                if (Houdaobus != null)
                    total = Houdaobus.size();
                else
                    Houdaobus = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        resultSet.put("data", Houdaobus);
        resultSet.put("total", total);
        return resultSet;
    }

    public JSONObject updateHoudaobu(JSONObject jsonObject){
        Houdaobut houdaobut = new Houdaobut();
        Houdaobut houdaobut1 = new Houdaobut();
        Houdaobu houdaobu = new Houdaobu();
        JSONObject res = new JSONObject();
        houdaobut = houdaobuMapper.selectHoudaobutById(jsonObject.getInteger("id"));
        houdaobu = houdaobuMapper.selectKuanhaoYanse(houdaobut.getKuanhao(),houdaobut.getYanse());
        houdaobut1.setKuanhao(houdaobut.getKuanhao());
        houdaobut1.setYanse(houdaobut.getYanse());
        houdaobut.setXs(jsonObject.getInteger("xs"));
        houdaobut1.setS(jsonObject.getInteger("s"));
        houdaobut1.setM(jsonObject.getInteger("m"));
        houdaobut1.setL(jsonObject.getInteger("l"));
        houdaobut1.setXl(jsonObject.getInteger("xl"));
        houdaobut1.setXxl(jsonObject.getInteger("xxl"));
        houdaobut1.setXxxl(jsonObject.getInteger("xxxl"));
        if(houdaobuMapper.updateHoudaobut(houdaobut1)){
            houdaobu.setHdbshuliang(houdaobu.getHdbshuliang()-houdaobut.getXs()-houdaobut.getS()-houdaobut.getM()-houdaobut.getL()-
                    houdaobut.getXl()-houdaobut.getXl()-houdaobut.getXxl()-houdaobut.getXxxl()+
                    houdaobut1.getXs()+houdaobut1.getS()+houdaobut1.getM()+houdaobut1.getL()+houdaobut1.getXl()+
                    houdaobut1.getXxl()+houdaobut1.getXxxl());
            res.put("result","success");
        }else{
            res.put("result","error");
        }
        return res;
    }

}
