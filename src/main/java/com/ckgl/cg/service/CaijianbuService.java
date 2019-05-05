package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Caijianbu;
import com.ckgl.cg.dao.CaijianbuMapper;
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


    public Map<String, Object> selectByKuanhao(String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Caijianbu> caijianbus = new ArrayList<>();
        long total = 0;

        Caijianbu caijianbu = null;
        try {
            caijianbu = caijianbuMapper.selectByKuanhao(kuanhao);
        } catch (PersistenceException e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }

        if (caijianbu != null) {
            caijianbus.add(caijianbu);
            total = 1;
        }

        resultSet.put("data", caijianbus);
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
        List<Caijianbu> caijianbus = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                caijianbus = caijianbuMapper.selectAll();
                if (caijianbus != null) {
                    PageInfo<Caijianbu> pageInfo = new PageInfo<>(caijianbus);
                    total = pageInfo.getTotal();
                } else
                    caijianbus = new ArrayList<>();
            } else {
                caijianbus = caijianbuMapper.selectAll();
                if (caijianbus != null)
                    total = caijianbus.size();
                else
                    caijianbus = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", caijianbus);
        resultSet.put("total", total);
        return resultSet;
    }

    public JSONObject delete(String jsonObject){
        JSONObject js2 = new JSONObject();
        Caijianbu caijianbu = caijianbuMapper.selectByKuanhao(jsonObject);
        if(caijianbu!=null){
            if (caijianbuMapper.delete(jsonObject)){
                js2.put("result","success");
            }
        }else {
            js2.put("result","error");
        }
        return js2;
    }

}
