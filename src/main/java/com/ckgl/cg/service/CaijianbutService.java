package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Caijianbu;
import com.ckgl.cg.bean.Caijianbut;
import com.ckgl.cg.dao.CaijianbuMapper;
import com.ckgl.cg.dao.CaijianbutMapper;
import com.ckgl.cg.dao.HoudaobuMapper;
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

    public Map<String, Object> selectByKuanhao(String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Caijianbut> caijianbuts = new ArrayList<>();
        long total = 0;

        Caijianbut caijianbut = null;
        try {
            caijianbut = caijianbutMapper.selectByKuanhao(kuanhao);
        } catch (PersistenceException e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }

        if (caijianbut != null) {
            caijianbuts.add(caijianbut);
            total = 1;
        }

        resultSet.put("data", caijianbuts);
        resultSet.put("total", total);
        return resultSet;
    }

    public JSONObject insert(JSONObject jsonObject) {
        Caijianbut caijianbut = new Caijianbut();
        Caijianbu caijianbu = new Caijianbu();
        JSONObject js1 = new JSONObject();
        JSONObject js2 = new JSONObject();
        caijianbut.setKuanhao(jsonObject.getString("kuanhao"));
        caijianbut.setYanse(jsonObject.getString("yanse"));
        caijianbut.setCjriqi(jsonObject.getString("cjriqi"));
        caijianbut.setXs(jsonObject.getInteger("xs"));
        caijianbut.setS(jsonObject.getInteger("s"));
        caijianbut.setM(jsonObject.getInteger("m"));
        caijianbut.setL(jsonObject.getInteger("l"));
        caijianbut.setXl(jsonObject.getInteger("xl"));
        caijianbut.setXxl(jsonObject.getInteger("xxl"));
        caijianbut.setXxxl(jsonObject.getInteger("xxxl"));
        caijianbu.setKuanhao(jsonObject.getString("kuanhao"));
        caijianbu.setCjbshuliang(jsonObject.getInteger("cjbshuliang"));
        if (caijianbutMapper.insert(caijianbut)) {
            if (caijianbuMapper.selectByKuanhao(caijianbu.getKuanhao())==null) {
                caijianbuMapper.insert(caijianbu);
                js2.put("result","success");
            }else{
                caijianbuMapper.update(caijianbu);
                js2.put("result", "success");
            }
            js1.put("result", "success");
        }else {
            js1.put("result","error");
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
        List<Caijianbut> caijianbuts = null;
        long total = 0;
        boolean isPagination = true;

        // validate
        if (offset < 0 || limit < 0)
            isPagination = false;

        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                caijianbuts = caijianbutMapper.findByKuanhao(kuanhao);
                if (caijianbuts != null) {
                    PageInfo<Caijianbut> pageInfo = new PageInfo<>(caijianbuts);
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
}

