package com.ckgl.cg.service;

import com.ckgl.cg.bean.Jincang;
import com.ckgl.cg.dao.JincangMapper;
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
public class JincangService {

    @Autowired
    private JincangMapper jincangMapper;

    public Map<String, Object> selectByKuanhao(String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Jincang> jincangs = new ArrayList<>();
        long total = 0;

        Jincang jincang = null;
        try {
            jincang = jincangMapper.selectByKuanhao(kuanhao);
        } catch (PersistenceException e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }

        if (jincang != null) {
            jincangs.add(jincang);
            total = 1;
        }

        resultSet.put("data", jincangs);
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
        List<Jincang> jincangs = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                jincangs = jincangMapper.selectAll();
                if (jincangs != null) {
                    PageInfo<Jincang> pageInfo = new PageInfo<>(jincangs);
                    total = pageInfo.getTotal();
                } else
                    jincangs = new ArrayList<>();
            } else {
                jincangs = jincangMapper.selectAll();
                if (jincangs != null)
                    total = jincangs.size();
                else
                    jincangs = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", jincangs);
        resultSet.put("total", total);
        return resultSet;
    }
}
