package com.ckgl.cg.service;

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
    private CaijianbutMapper caijianbutMapper;
    @Autowired
    private CaijianbuMapper caijianbuMapper;


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
                caijianbuts = caijianbuMapper.selectByKuanhao2(kuanhao);
                if (caijianbuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(caijianbuts);
                    total = pageInfo.getTotal();
                } else
                    caijianbuts = new ArrayList<>();
            } else {
                caijianbuts = caijianbuMapper.selectByKuanhao2(kuanhao);
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
        List<Map> Caijianbus = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                Caijianbus = caijianbuMapper.selectAll();
                if (Caijianbus != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(Caijianbus);
                    total = pageInfo.getTotal();
                } else
                    Caijianbus = new ArrayList<>();
            } else {
                Caijianbus = caijianbuMapper.selectAll();
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


}
