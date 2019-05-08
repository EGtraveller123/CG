package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Jincangt;
import com.ckgl.cg.bean.Kucun;
import com.ckgl.cg.bean.Kucunt;
import com.ckgl.cg.dao.JincangtMapper;
import com.ckgl.cg.dao.KucunMapper;
import com.ckgl.cg.dao.KucuntMapper;
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
public class JincangtService {
    @Autowired
    private JincangtMapper JincangtMapper;

    @Autowired
    private KucuntMapper kucuntMapper;

    @Autowired
    private KucunMapper kucunMapper;

    public Map<String, Object> selectByKuanhao(String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Map> Jincangs = null;
        long total = 0;
        try {
            Jincangs = JincangtMapper.selectByKuanhao(kuanhao);
        } catch (PersistenceException e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }

        resultSet.put("data", Jincangs);
        resultSet.put("total", total);
        return resultSet;
    }

    public JSONObject insert(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        Kucunt kucunt = new Kucunt();
        Kucunt kucunt1 = new Kucunt();
        Jincangt Jincangt = new Jincangt();
        kucunt1 = kucuntMapper.selectByKuanhaoYanse(jsonObject.getString("kuanhao"),jsonObject.getString("yanse"));
        Jincangt.setJcriqi(jsonObject.getString("jcriqi"));
        Jincangt.setL(jsonObject.getInteger("l"));
        Jincangt.setXs(jsonObject.getInteger("xs"));
        Jincangt.setS(jsonObject.getInteger("s"));
        Jincangt.setM(jsonObject.getInteger("m"));
        Jincangt.setXl(jsonObject.getInteger("xl"));
        Jincangt.setXxl(jsonObject.getInteger("xxl"));
        Jincangt.setXxxl(jsonObject.getInteger("xxxl"));
        Jincangt.setBeizhu(jsonObject.getString("beizhu"));
        if (kucunt1 != null){
            kucunt.setId(kucunt1.getId());
            kucunt.setKuanhao(jsonObject.getString("kuanhao"));
            kucunt.setYanse(jsonObject.getString("yanse"));
            kucunt.setL(kucunt1.getL()+jsonObject.getInteger("l"));
            kucunt.setXs(kucunt1.getXs()+jsonObject.getInteger("xs"));
            kucunt.setS(kucunt1.getS()+jsonObject.getInteger("s"));
            kucunt.setM(kucunt1.getM()+jsonObject.getInteger("m"));
            kucunt.setXl(kucunt1.getXl()+jsonObject.getInteger("xl"));
            kucunt.setXxl(kucunt1.getXxl()+jsonObject.getInteger("xxl"));
            kucunt.setXxxl(kucunt1.getXxxl()+jsonObject.getInteger("xxxl"));
            kucuntMapper.updateKucunt(kucunt);
            kucunMapper.updateKucun(kucunt1.getKuanhao());
            Jincangt.setId(kucunt1.getId());
            JincangtMapper.insert(Jincangt);
            result.put("result", "success");
            }else {
            kucunt.setKuanhao(jsonObject.getString("kuanhao"));
            kucunt.setYanse(jsonObject.getString("yanse"));
            kucunt.setL(jsonObject.getInteger("l"));
            kucunt.setXs(jsonObject.getInteger("xs"));
            kucunt.setS(jsonObject.getInteger("s"));
            kucunt.setM(jsonObject.getInteger("m"));
            kucunt.setXl(jsonObject.getInteger("xl"));
            kucunt.setXxl(jsonObject.getInteger("xxl"));
            kucunt.setXxxl(jsonObject.getInteger("xxxl"));
            kucuntMapper.insertKucunt(kucunt);
            kucunMapper.insertKucun(jsonObject.getString("kuanhao"));
            Jincangt.setKucunid(kucuntMapper.selectByKuanhaoYanse(jsonObject.getString("kuanhao"),jsonObject.getString("yanse")).getId());
            JincangtMapper.insert(Jincangt);
            result.put("result", "success");
            }
        return result;
    }

    /**
     * @param offset 分页的偏移值
     * @param limit  分页的大小
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    public Map<String, Object> selectAll(int offset, int limit) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> Jincangts = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                Jincangts = JincangtMapper.selectAll();
                if (Jincangts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(Jincangts);
                    total = pageInfo.getTotal();
                } else
                    Jincangts = new ArrayList<>();
            } else {
                Jincangts = JincangtMapper.selectAll();
                if (Jincangts != null)
                    total = Jincangts.size();
                else
                    Jincangts = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", Jincangts);
        resultSet.put("total", total);
        return resultSet;
    }
}
