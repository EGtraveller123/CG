package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Jincang;
import com.ckgl.cg.bean.Jincangt;
import com.ckgl.cg.bean.Kucun;
import com.ckgl.cg.bean.Kucunt;
import com.ckgl.cg.dao.JincangMapper;
import com.ckgl.cg.dao.JincangtMapper;
import com.ckgl.cg.dao.KucunMapper;
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
    private JincangtMapper jincangtMapper;

    @Autowired
    private JincangMapper jincangMapper;

    @Autowired
    private KucunMapper kucunMapper;

    public Map<String, Object> selectByKuanhao(String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Jincangt> jincangts = new ArrayList<>();
        long total = 0;

        Jincangt jincangt = null;
        try {
            jincangt = jincangtMapper.selectByKuanhao(kuanhao);
        } catch (PersistenceException e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }

        if (jincangt != null) {
            jincangts.add(jincangt);
            total = 1;
        }

        resultSet.put("data", jincangts);
        resultSet.put("total", total);
        return resultSet;
    }

    public JSONObject insert(JSONObject jsonObject) {
        Jincangt jincangt = new Jincangt();
        Kucunt kucunt = new Kucunt();
        Kucun kucun = new Kucun();
        Kucunt kucunt1 =new Kucunt();
        Jincang jincang = new Jincang();
        JSONObject js1 = new JSONObject();
        jincangt.setKuanhao(jsonObject.getString("kuanhao"));
        jincangt.setYanse(jsonObject.getString("yanse"));
        jincangt.setJcriqi(jsonObject.getString("jcriqi"));
        jincangt.setXs(jsonObject.getInteger("xs"));
        jincangt.setS(jsonObject.getInteger("s"));
        jincangt.setM(jsonObject.getInteger("m"));
        jincangt.setL(jsonObject.getInteger("l"));
        jincangt.setXl(jsonObject.getInteger("xl"));
        jincangt.setXxl(jsonObject.getInteger("xxl"));
        jincangt.setXxxl(jsonObject.getInteger("xxxl"));
        jincang.setKuanhao(jsonObject.getString("kuanhao"));
        jincang.setJcshuliang(jsonObject.getInteger("jcshuliang"));
        kucunt.setKuanhao(jsonObject.getString("kuanhao"));
        kucunt.setYanse(jsonObject.getString("yanse"));
        kucunt.setXs(jsonObject.getInteger("xs"));
        kucunt.setS(jsonObject.getInteger("s"));
        kucunt.setM(jsonObject.getInteger("m"));
        kucunt.setL(jsonObject.getInteger("l"));
        kucunt.setXl(jsonObject.getInteger("xl"));
        kucunt.setXxl(jsonObject.getInteger("xxl"));
        kucunt.setXxxl(jsonObject.getInteger("xxxl"));
        kucun.setKuanhao(jsonObject.getString("kuanhao"));
        kucunt1=kucunMapper.findByKuanhaoYanse(kucunt.getKuanhao(),kucunt.getYanse());
        if (jincangtMapper.insert(jincangt)) {
            js1.put("result1","success");
            if(kucunt1==null){
                if(jincangtMapper.insertKucunt(kucunt)){
                    js1.put("result2","success");
                }else {
                    js1.put("result2","error");
                }
            }else {
                kucunt.setXs(kucunt.getXs()+kucunt1.getXs());
                kucunt.setS(kucunt.getS()+kucunt1.getS());
                kucunt.setM(kucunt.getM()+kucunt1.getM());
                kucunt.setL(kucunt.getL()+kucunt1.getL());
                kucunt.setXl(kucunt.getXl()+kucunt1.getXl());
                kucunt.setXxl(kucunt.getXxl()+kucunt1.getXxl());
                kucunt.setXxxl(kucunt.getXxxl()+kucunt1.getXxxl());
                if(jincangtMapper.updateKucunt(kucunt)){
                    js1.put("result2","success");
                }else {
                    js1.put("result2","error");
                }
            }
        }else {
            js1.put("result1","error");
        }
        return js1;
    }

    /**
     * @param offset 分页的偏移值
     * @param limit  分页的大小
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    public Map<String, Object> findByKuanhao(int offset, int limit, String kuanhao) {
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Jincangt> jincangts = null;
        long total = 0;
        boolean isPagination = true;

        // validate
        if (offset < 0 || limit < 0)
            isPagination = false;

        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                jincangts = jincangtMapper.findByKuanhao(kuanhao);
                if (jincangts != null) {
                    PageInfo<Jincangt> pageInfo = new PageInfo<>(jincangts);
                    total = pageInfo.getTotal();
                } else
                    jincangts = new ArrayList<>();
            } else {
                jincangts = jincangtMapper.findByKuanhao(kuanhao);
                if (jincangts != null)
                    total = jincangts.size();
                else
                    jincangts = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", jincangts);
        resultSet.put("total", total);
        return resultSet;
    }
}
