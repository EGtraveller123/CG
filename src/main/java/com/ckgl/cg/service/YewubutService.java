package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Yewubu;
import com.ckgl.cg.bean.Yewubut;
import com.ckgl.cg.dao.YewubuMapper;
import com.ckgl.cg.dao.YewubutMapper;
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
public class YewubutService {
    @Autowired
    private YewubutMapper yewubutMapper;





    public JSONObject insert(JSONObject jsonObject){
        Yewubut yewubut = new Yewubut();
        JSONObject js1 = new JSONObject();
        yewubut.setKuanhao(js1.getString("kuanhao"));
        yewubut.setYanse(js1.getString("yanse"));
        yewubut.setXs(js1.getInteger("xs"));
        yewubut.setS(js1.getInteger("s"));
        yewubut.setM(js1.getInteger("m"));
        yewubut.setL(js1.getInteger("l"));
        yewubut.setXl(js1.getInteger("xl"));
        yewubut.setXxl(js1.getInteger("xxl"));
        yewubut.setXxxl(js1.getInteger("xxxl"));
        if(yewubutMapper.insert(yewubut)){
            js1.put("result","success");
        }else{
            js1.put("result","error");
        }
        return js1;
    }

    public Map<String, Object> selectByKuanhao(String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Yewubut> caijianbuts = new ArrayList<>();
        long total = 0;

        Yewubut caijianbut = null;
        try {
            caijianbut = yewubutMapper.selectByKuanhao(kuanhao);
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


    public JSONObject delete(JSONObject jsonObject){
        JSONObject js2 = new JSONObject();
        if(yewubutMapper.delete(jsonObject.getString("kuanhao"))){
            js2.put("result","success");
        }else{
            js2.put("result","error");
        }
        return js2;
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
        List<Yewubut> yewubuts = null;
        long total = 0;
        boolean isPagination = true;

        // validate
        if (offset < 0 || limit < 0)
            isPagination = false;

        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                yewubuts = yewubutMapper.findByKuanhao(kuanhao);
                if (yewubuts != null) {
                    PageInfo<Yewubut> pageInfo = new PageInfo<>(yewubuts);
                    total = pageInfo.getTotal();
                } else
                    yewubuts = new ArrayList<>();
            } else {
                yewubuts = yewubutMapper.findByKuanhao(kuanhao);
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
