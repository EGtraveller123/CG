package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Chucangt;
import com.ckgl.cg.bean.Kucun;
import com.ckgl.cg.bean.Kucunt;
import com.ckgl.cg.dao.ChucangtMapper;
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
public class ChucangtService {
    @Autowired
    private ChucangtMapper chucangtMapper;

    @Autowired
    private KucuntMapper kucuntMapper;

    @Autowired
    private KucunMapper kucunMapper;

    public Map<String, Object> selectByKuanhao(String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Map> chucangs = null;
        long total = 0;
        try {
            chucangs = chucangtMapper.selectByKuanhao(kuanhao);
        } catch (PersistenceException e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }

        resultSet.put("data", chucangs);
        resultSet.put("total", total);
        return resultSet;
    }

    public JSONObject insert(JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        Kucunt kucunt = new Kucunt();
        Kucunt kucunt1 = new Kucunt();
        Chucangt chucangt = new Chucangt();
        kucunt1 = kucuntMapper.selectByKuanhaoYanse(jsonObject.getString("kuanhao"),jsonObject.getString("yanse"));
        if (kucunt1 != null){
            chucangt.setCcriqi(jsonObject.getString("ccriqi"));
            chucangt.setL(jsonObject.getInteger("l"));
            chucangt.setXs(jsonObject.getInteger("xs"));
            chucangt.setS(jsonObject.getInteger("s"));
            chucangt.setM(jsonObject.getInteger("m"));
            chucangt.setXl(jsonObject.getInteger("xl"));
            chucangt.setXxl(jsonObject.getInteger("xxl"));
            chucangt.setXxxl(jsonObject.getInteger("xxxl"));
            chucangt.setBeizhu(jsonObject.getString("beizhu"));
            if(chucangt.getL()<=kucunt1.getL()
                    &&chucangt.getM()<=kucunt1.getM()
                    &&chucangt.getS()<=kucunt1.getS()
                    &&chucangt.getXs()<=kucunt1.getXs()
                    &&chucangt.getXl()<=kucunt1.getXl()
                    &&chucangt.getXxl()<=kucunt1.getXxl()
                    &&chucangt.getXxxl()<=kucunt1.getXxxl()){
                kucunt.setId(kucunt1.getId());
                kucunt.setKuanhao(jsonObject.getString("kuanhao"));
                kucunt.setYanse("yanse");
                kucunt.setXs(kucunt1.getXs()-jsonObject.getInteger("xs"));
                kucunt.setS(kucunt1.getS()-jsonObject.getInteger("s"));
                kucunt.setM(kucunt1.getM()-jsonObject.getInteger("m"));
                kucunt.setL(kucunt1.getL()-jsonObject.getInteger("l"));
                kucunt.setXl(kucunt1.getXl()-jsonObject.getInteger("xl"));
                kucunt.setXxl(kucunt1.getXxl()-jsonObject.getInteger("xxl"));
                kucunt.setXxxl(kucunt1.getXxxl()-jsonObject.getInteger("xxxl"));
                kucuntMapper.updateKucunt(kucunt);
                kucunMapper.updateKucun(kucunt1.getKuanhao());
                chucangt.setKucunid(kucunt1.getId());
                chucangtMapper.insert(chucangt);
                result.put("result", "success");
                if(kucuntMapper.findKucuntSum(kucunt1.getId())==0){
                    kucuntMapper.deleteKucunt(kucunt1.getId());
                    if(kucunMapper.selectByKuanhao(kucunt1.getKuanhao()).getKcshuliang()==0){
                        kucunMapper.deleteKucun(kucunt1.getKuanhao());
                    }
                }
            }else {
                result.put("result", "库存数量不足请查看库存");
            }
        }else {
            result.put("result", "仓库中没有此物品");
        }
        return result;
    }

    /**
     * @param offset 分页的偏移值
     * @param limit  分页的大小
     * @return 结果的一个Map，其中： key为 data 的代表记录数据；key 为 total 代表结果记录的数量
     */
    public Map<String, Object> selectAll(int offset, int limit) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Map> chucangts = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                chucangts = chucangtMapper.selectAll();
                if (chucangts != null) {
                    PageInfo<Map> pageInfo = new PageInfo<>(chucangts);
                    total = pageInfo.getTotal();
                } else
                    chucangts = new ArrayList<>();
            } else {
                chucangts = chucangtMapper.selectAll();
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
