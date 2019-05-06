package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Chucang;
import com.ckgl.cg.bean.Chucangt;
import com.ckgl.cg.bean.Kucunt;
import com.ckgl.cg.dao.ChucangMapper;
import com.ckgl.cg.dao.ChucangtMapper;
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
public class ChucangtService {
    @Autowired
    private ChucangtMapper chucangtMapper;

    @Autowired
    private ChucangMapper chucangMapper;

    @Autowired
    private KucunMapper kucunMapper;

    public Map<String, Object> selectByKuanhao(String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Chucangt> chucangts = new ArrayList<>();
        long total = 0;

        Chucangt chucangt = null;
        try {
            chucangt = chucangtMapper.selectByKuanhao(kuanhao);
        } catch (PersistenceException e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }

        if (chucangt != null) {
            chucangts.add(chucangt);
            total = 1;
        }

        resultSet.put("data", chucangts);
        resultSet.put("total", total);
        return resultSet;
    }

    public JSONObject insert(JSONObject jsonObject) {
        Chucangt chucangt = new Chucangt();
        Chucang chucang = new Chucang();
        Kucunt kucunt = new Kucunt();
        JSONObject js1 = new JSONObject();
        JSONObject js2 = new JSONObject();
        JSONObject js3 = new JSONObject();
        chucangt.setKuanhao(jsonObject.getString("kuanhao"));
        chucangt.setYanse(jsonObject.getString("yanse"));
        chucangt.setCcriqi(jsonObject.getString("ccriqi"));
        chucangt.setXs(jsonObject.getInteger("xs"));
        chucangt.setS(jsonObject.getInteger("s"));
        chucangt.setM(jsonObject.getInteger("m"));
        chucangt.setL(jsonObject.getInteger("l"));
        chucangt.setXl(jsonObject.getInteger("xl"));
        chucangt.setXxl(jsonObject.getInteger("xxl"));
        chucangt.setXxxl(jsonObject.getInteger("xxxl"));
        chucang.setKuanhao(jsonObject.getString("kuanhao"));
        chucang.setCcshuliang(jsonObject.getInteger("ccshuliang"));
        kucunt.setKuanhao(jsonObject.getString("kuanhao"));
        kucunt.setYanse(jsonObject.getString("yanse"));
        kucunt.setXs(jsonObject.getInteger("xs"));
        kucunt.setS(jsonObject.getInteger("s"));
        kucunt.setM(jsonObject.getInteger("m"));
        kucunt.setL(jsonObject.getInteger("l"));
        kucunt.setXl(jsonObject.getInteger("xl"));
        kucunt.setXxl(jsonObject.getInteger("xxl"));
        kucunt.setXxxl(jsonObject.getInteger("xxxl"));
        if (chucangtMapper.insert(chucangt)) {
            kucunMapper.insertCC(kucunt);
            js3.put("result","success");
            if (chucangMapper.selectByKuanhao(chucang.getKuanhao())==null) {
                chucangMapper.insert(chucang);
                js2.put("result","success");
            }else{
                chucangMapper.update(chucang);
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
        List<Chucangt> chucangts = null;
        long total = 0;
        boolean isPagination = true;

        // validate
        if (offset < 0 || limit < 0)
            isPagination = false;

        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                chucangts = chucangtMapper.findByKuanhao(kuanhao);
                if (chucangts != null) {
                    PageInfo<Chucangt> pageInfo = new PageInfo<>(chucangts);
                    total = pageInfo.getTotal();
                } else
                    chucangts = new ArrayList<>();
            } else {
                chucangts = chucangtMapper.findByKuanhao(kuanhao);
                if (chucangts != null)
                    total = chucangts.size();
                else
                    chucangts = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", chucangts);
        resultSet.put("total", total);
        return resultSet;
    }
}
