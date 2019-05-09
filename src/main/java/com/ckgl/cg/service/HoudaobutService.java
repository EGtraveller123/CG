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
public class HoudaobutService {
    @Autowired
    private HoudaobutMapper houdaobutMapper;

    @Autowired
    private HoudaobuMapper houdaobuMapper;

    public Map<String, Object> selectByKuanhao(int offset, int limit, String kuanhao) {
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
                houdaobuts = houdaobutMapper.selectByKuanhao(kuanhao);
                if (houdaobuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(houdaobuts);
                    total = pageInfo.getTotal();
                } else
                    houdaobuts = new ArrayList<>();
            } else {
                houdaobuts = houdaobutMapper.selectByKuanhao(kuanhao);
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

    public JSONObject insert(JSONObject jsonObject) {
        Houdaobut houdaobut = new Houdaobut();
        JSONObject res = new JSONObject();
        houdaobut.setKuanhao(jsonObject.getString("kuanhao"));
        houdaobut.setYanse(jsonObject.getString("yanse"));

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
        List<Map> houdaobuts = null;
        long total = 0;
        boolean isPagination = true;

        // validate
        if (offset < 0 || limit < 0)
            isPagination = false;

        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                houdaobuts = houdaobutMapper.findByKuanhao(kuanhao);
                if (houdaobuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(houdaobuts);
                    total = pageInfo.getTotal();
                } else
                    houdaobuts = new ArrayList<>();
            } else {
                houdaobuts = houdaobutMapper.findByKuanhao(kuanhao);
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
}
