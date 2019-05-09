package com.ckgl.cg.service;


import com.ckgl.cg.dao.YewubutMapper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Service
public class YewubuService {

    @Autowired
    private YewubutMapper yewubutMapper;

    public Map<String, Object> selectAll(int offset, int limit) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> yewubuts = null;
        long total = 0;
        boolean isPagination = true;
        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                yewubuts = yewubutMapper.selectAll();
                if (yewubuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(yewubuts);
                    total = pageInfo.getTotal();
                } else
                    yewubuts = new ArrayList<>();
            } else {
                yewubuts = yewubutMapper.selectAll();
                if (yewubuts != null)
                    total = yewubuts.size();
                else
                    yewubuts = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        resultSet.put("data", yewubuts);
        resultSet.put("total", total);
        return resultSet;
    }

    public Map<String, Object> selectByKuanhao(int offset, int limit, String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> yewubuts = null;
        long total = 0;
        boolean isPagination = true;
        if (offset < 0 || limit < 0)
            isPagination = false;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                yewubuts = yewubutMapper.selectByKuanhao(kuanhao);
                if (yewubuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(yewubuts);
                    total = pageInfo.getTotal();
                } else
                    yewubuts = new ArrayList<>();
            } else {
                yewubuts = yewubutMapper.selectByKuanhao(kuanhao);
                if (yewubuts != null)
                    total = yewubuts.size();
                else
                    yewubuts = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        resultSet.put("data", yewubuts);
        resultSet.put("total", total);
        return resultSet;
    }

    public Map<String, Object> selectByKehu(int offset, int limit, String kehu) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> yewubuts = null;
        long total = 0;
        boolean isPagination = true;
        if (offset < 0 || limit < 0)
            isPagination = false;
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                yewubuts = yewubutMapper.selectByKehu(kehu);
                if (yewubuts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(yewubuts);
                    total = pageInfo.getTotal();
                } else
                    yewubuts = new ArrayList<>();
            } else {
                yewubuts = yewubutMapper.selectByKehu(kehu);
                if (yewubuts != null)
                    total = yewubuts.size();
                else
                    yewubuts = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        resultSet.put("data", yewubuts);
        resultSet.put("total", total);
        return resultSet;
    }
}
