package com.ckgl.cg.service;

import com.ckgl.cg.bean.Chucang;
import com.ckgl.cg.dao.ChucangMapper;
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
public class ChucangService {

    @Autowired
    private ChucangMapper chucangMapper;

    public Map<String, Object> selectByKuanhao(String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Chucang> chucangs = new ArrayList<>();
        long total = 0;

        Chucang chucang = null;
        try {
            chucang = chucangMapper.selectByKuanhao(kuanhao);
        } catch (PersistenceException e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }

        if (chucang != null) {
            chucangs.add(chucang);
            total = 1;
        }

        resultSet.put("data", chucangs);
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
        List<Chucang> chucangs = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                chucangs = chucangMapper.selectAll();
                if (chucangs != null) {
                    PageInfo<Chucang> pageInfo = new PageInfo<>(chucangs);
                    total = pageInfo.getTotal();
                } else
                    chucangs = new ArrayList<>();
            } else {
                chucangs = chucangMapper.selectAll();
                if (chucangs != null)
                    total = chucangs.size();
                else
                    chucangs = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", chucangs);
        resultSet.put("total", total);
        return resultSet;
    }
}
