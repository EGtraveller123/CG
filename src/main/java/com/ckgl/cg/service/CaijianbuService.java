package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Caijianbu;
import com.ckgl.cg.bean.Caijianbut;
import com.ckgl.cg.dao.CaijianbuMapper;
import com.ckgl.cg.dao.CaijianbutMapper;
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
public class CaijianbuService {
    @Autowired
    private CaijianbuMapper caijianbuMapper;

    @Autowired
    private CaijianbutMapper caijianbutMapper;


    public Map<String, Object> selectByKuanhao(int offset, int limit, String kuanhao,String sortName,String sortOrder) {
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
                caijianbuts = caijianbuMapper.selectByKuanhao2(kuanhao,sortOrder,sortName);
                if (caijianbuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(caijianbuts);
                    total = pageInfo.getTotal();
                } else
                    caijianbuts = new ArrayList<>();
            } else {
                caijianbuts = caijianbuMapper.selectByKuanhao2(kuanhao,sortOrder,sortName);
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



    public Map<String, Object> selectAll(int offset, int limit,String sortName,String sortOrder) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> Caijianbus = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                Caijianbus = caijianbuMapper.selectAll(sortName,sortOrder);
                if (Caijianbus != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(Caijianbus);
                    total = pageInfo.getTotal();
                } else
                    Caijianbus = new ArrayList<>();
            } else {
                Caijianbus = caijianbuMapper.selectAll(sortName,sortOrder);
                if (Caijianbus != null)
                    total = Caijianbus.size();
                else
                    Caijianbus = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", Caijianbus);
        resultSet.put("total", total);
        return resultSet;
    }

    public Map<String, Object> selectByYewubu(int offset, int limit,String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> Caijianbus = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                Caijianbus = caijianbuMapper.selectByYewubu(kuanhao);
                if (Caijianbus != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(Caijianbus);
                    total = pageInfo.getTotal();
                } else
                    Caijianbus = new ArrayList<>();
            } else {
                Caijianbus = caijianbuMapper.selectByYewubu(kuanhao);
                if (Caijianbus != null)
                    total = Caijianbus.size();
                else
                    Caijianbus = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", Caijianbus);
        resultSet.put("total", total);
        return resultSet;
    }

    public JSONObject updateCaijianbut(JSONObject jsonObject){
        Caijianbut caijianbut = new Caijianbut();
        Caijianbu caijianbu = new Caijianbu();
        Caijianbut caijianbut1 = new Caijianbut();
        JSONObject res = new JSONObject();
        caijianbut=caijianbutMapper.selectCaijianbutById(jsonObject.getInteger("id"));
        caijianbu=caijianbuMapper.selectKuanhaoYanse(caijianbut.getKuanhao(),caijianbut.getYanse());
        caijianbut1.setId(caijianbut.getId());
        caijianbut1.setKuanhao(caijianbut.getKuanhao());
        caijianbut1.setYanse(caijianbut.getYanse());
        caijianbut1.setCjriqi(jsonObject.getString("cjriqi"));
        caijianbut1.setXs(jsonObject.getInteger("xs"));
        caijianbut1.setS(jsonObject.getInteger("s"));
        caijianbut1.setM(jsonObject.getInteger("m"));
        caijianbut1.setL(jsonObject.getInteger("l"));
        caijianbut1.setXl(jsonObject.getInteger("xl"));
        caijianbut1.setXxl(jsonObject.getInteger("xxl"));
        caijianbut1.setXxxl(jsonObject.getInteger("xxxl"));
        if(caijianbutMapper.updateCaijianbut(caijianbut1)) {
            caijianbu.setCjbshuliang(caijianbu.getCjbshuliang() -
                    caijianbut.getXs() - caijianbut.getS() - caijianbut.getM() - caijianbut.getL() - caijianbut.getXl() -
                    caijianbut.getXxl() - caijianbut.getXxxl() + caijianbut1.getXs() + caijianbut1.getS() + caijianbut1.getM() +
                    caijianbut1.getL() + caijianbut1.getXl() + caijianbut1.getXxl() + caijianbut1.getXxxl());
            res.put("result", "success");
        }else {
            res.put("result","error");
        }
        return res;
    }


}
