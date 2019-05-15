package com.ckgl.cg.service;


import com.ckgl.cg.bean.Kucun;
import com.ckgl.cg.bean.Kucunt;
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
public class KucunService {

    @Autowired
    private KucunMapper kucunMapper;

    @Autowired
    private KucuntMapper kucuntMapper;

    public Map<String, Object> selectByKuanhao(String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Kucun> kucuns = new ArrayList<>();
        long total = 0;

        Kucun kucun = null;
        try {
            kucun = kucunMapper.selectByKuanhao(kuanhao);
        } catch (PersistenceException e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }

        if (kucun != null) {
            kucuns.add(kucun);
            total = 1;
        }

        resultSet.put("data", kucuns);
        resultSet.put("total", total);
        return resultSet;
    }

    /**
     * @param offset 分页的偏移值
     * @param limit  分页的大小
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    public Map<String, Object> selectAll(int offset, int limit) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Kucun> kucuns = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                kucuns = kucunMapper.selectAll();
                if (kucuns != null) {
                    PageInfo<Kucun> pageInfo = new PageInfo<>(kucuns);
                    total = pageInfo.getTotal();
                } else
                    kucuns = new ArrayList<>();
            } else {
                kucuns = kucunMapper.selectAll();
                if (kucuns != null)
                    total = kucuns.size();
                else
                    kucuns = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", kucuns);
        resultSet.put("total", total);
        return resultSet;
    }


//    public Map<String, Object> findByKuanhao(String kuanhao) {
//        Map<String, Object> resultSet = new HashMap<>();
//        List<Kucunt> kucunts = new ArrayList<>();
//        long total = 0;
//
//        try {
//            kucunts = kucuntMapper.selectByKuanhao(kuanhao);
//        } catch (PersistenceException e) {
//            System.out.println("exception catch");
//            e.printStackTrace();
//        }
//        resultSet.put("data", kucunts);
//        resultSet.put("total", total);
//        return resultSet;
//    }

    public Map<String, Object> findByKuanhao(int offset, int limit,String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Kucunt> kucunts = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                kucunts = kucuntMapper.selectByKuanhao(kuanhao);
                if (kucunts != null) {
                    PageInfo<Kucunt> pageInfo = new PageInfo<>(kucunts);
                    total = pageInfo.getTotal();
                } else
                    kucunts = new ArrayList<>();
            } else {
                kucunts = kucuntMapper.selectByKuanhao(kuanhao);
                if (kucunts != null)
                    total = kucunts.size();
                else
                    kucunts = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", kucunts);
        resultSet.put("total", total);
        return resultSet;
    }

}
