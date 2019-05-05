package com.ckgl.cg.service;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Caijianbu;
import com.ckgl.cg.bean.Houdaobu;
import com.ckgl.cg.bean.Yewubu;
import com.ckgl.cg.bean.Yewubut;
import com.ckgl.cg.dao.CaijianbuMapper;
import com.ckgl.cg.dao.HoudaobuMapper;
import com.ckgl.cg.dao.YewubuMapper;
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
    private YewubuMapper yewubuMapper;

    @Autowired
    private YewubutMapper yewubutMapper;

    @Autowired
    private CaijianbuMapper caijianbuMapper;

    @Autowired
    private HoudaobuMapper houdaobuMapper;

    public Map<String, Object> selectByKuanhao(String kuanhao) {
        Map<String, Object> resultSet = new HashMap<>();
        List<Yewubu> yewubus = new ArrayList<>();
        long total = 0;

        Yewubu yewubu = null;
        try {
            yewubu = yewubuMapper.selectByKuanhao(kuanhao);
        } catch (PersistenceException e) {
            System.out.println("exception catch");
            e.printStackTrace();
        }

        if (yewubu != null) {
            yewubus.add(yewubu);
            total = 1;
        }

        resultSet.put("data", yewubus);
        resultSet.put("total", total);
        return resultSet;
    }

    public Map<String, Object> selectAll(int offset, int limit) {
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Yewubu> yewubus = null;
        long total = 0;
        boolean isPagination = true;

        if (offset < 0 || limit < 0)
            isPagination = false;
        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                yewubus = yewubuMapper.selectAll();
                if (yewubus != null) {
                    PageInfo<Yewubu> pageInfo = new PageInfo<>(yewubus);
                    total = pageInfo.getTotal();
                } else
                    yewubus = new ArrayList<>();
            } else {
                yewubus = yewubuMapper.selectAll();
                if (yewubus != null)
                    total = yewubus.size();
                else
                    yewubus = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", yewubus);
        resultSet.put("total", total);
        return resultSet;
    }

    public Map<String, Object> selectByKehu(int offset, int limit, String kehu) {
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<>();
        PageHelper.startPage(offset,limit);
        List<Yewubu> yewubus = null;
        long total = 0;
        boolean isPagination = true;

        // validate
        if (offset < 0 || limit < 0)
            isPagination = false;

        // query
        try {
            if (isPagination) {
                PageHelper.offsetPage(offset, limit);
                yewubus = yewubuMapper.selectByKehu(kehu);
                if (yewubus != null) {
                    PageInfo<Yewubu> pageInfo = new PageInfo<>(yewubus);
                    total = pageInfo.getTotal();
                } else
                    yewubus = new ArrayList<>();
            } else {
                yewubus = yewubuMapper.selectByKehu(kehu);
                if (yewubus != null)
                    total = yewubus.size();
                else
                    yewubus = new ArrayList<>();
            }
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        resultSet.put("data", yewubus);
        resultSet.put("total", total);
        return resultSet;
    }

    public JSONObject insertYewu(JSONObject jsonObject){
        Yewubut yewubut = new Yewubut();
//        Yewubut yewubut1 = new Yewubut();
        JSONObject res = new JSONObject();
        yewubut.setKuanhao(jsonObject.getString("kuanhao"));
        yewubut.setYanse(jsonObject.getString("yanse"));
        yewubut.setXs(jsonObject.getInteger("xs"));
        yewubut.setS(jsonObject.getInteger("s"));
        yewubut.setM(jsonObject.getInteger("m"));
        yewubut.setL(jsonObject.getInteger("l"));
        yewubut.setXl(jsonObject.getInteger("xl"));
        yewubut.setXxl(jsonObject.getInteger("xxl"));
        yewubut.setXxxl(jsonObject.getInteger("xxxl"));

        List<Yewubut> yewubuts = yewubutMapper.selectKuanhao(yewubut.getKuanhao());
        int i = 0;

        for (Yewubut each:yewubuts) {
            if (yewubut.getKuanhao().equals(each.getKuanhao())) {
                if (yewubut.getYanse().equals(each.getYanse())) {
                    i++;
                }
            }
        }
        if(i>0){
            yewubuMapper.updateYewu2(yewubut);
            res.put("result","success");
        }else {
            yewubuMapper.insertYewu(yewubut);
            res.put("result","success");
        }
        return res;
    }

    public JSONObject insert(JSONObject jsonObject){
        Yewubu yewubu = new Yewubu();
        Caijianbu caijianbu = new Caijianbu();
        Houdaobu houdaobu = new Houdaobu();
        JSONObject res = new JSONObject();
        yewubu.setKuanhao(jsonObject.getString("kuanhao"));
        yewubu.setKehu(jsonObject.getString("kehu"));
        yewubu.setYwbshuliang(jsonObject.getInteger("ywbshuliang"));
        yewubu.setMianliao(jsonObject.getString("mianliao"));
        yewubu.setChriqi(jsonObject.getString("chriqi"));
        caijianbu.setKuanhao(jsonObject.getString("kuanhao"));
        caijianbu.setYwbshuliang(jsonObject.getInteger("ywbshuliang"));
        houdaobu.setKuanhao(jsonObject.getString("kuanhao"));
        houdaobu.setCjbshuliang(jsonObject.getInteger("cjbshuliang"));
        houdaobu.setHdbshuliang(jsonObject.getInteger("hdbshuliang"));
        if(yewubuMapper.insert(yewubu)){
            caijianbuMapper.insert(caijianbu);
            houdaobuMapper.insert(houdaobu);
            res.put("result","success");
            }else {
            res.put("result","error");
        }
        return res;
    }


    public JSONObject update(JSONObject jsonObject){
        Yewubu yewubu = new Yewubu();
        JSONObject js1 = new JSONObject();
        yewubu.setKuanhao(jsonObject.getString("kuanhao"));
        yewubu.setKehu(jsonObject.getString("kehu"));
        yewubu.setYwbshuliang(jsonObject.getInteger("ywbshuliang"));
        yewubu.setMianliao(jsonObject.getString("mianliao"));
        yewubu.setChriqi(jsonObject.getString("chriqi"));
        if (yewubuMapper.update(yewubu)){
            js1.put("result","success");
        }else{
            js1.put("result","error");
        }
        return js1;
    }

    public JSONObject delete(String jsonObject){
        JSONObject js2 = new JSONObject();
        Yewubu yewubu = yewubuMapper.selectByKuanhao(jsonObject);
        if(yewubu!=null){
            if (yewubuMapper.delete(jsonObject)){
                caijianbuMapper.delete(jsonObject);
                yewubuMapper.deleteYewubut(jsonObject);
                houdaobuMapper.delete(jsonObject);
                js2.put("result","success");
            }
        }else {
            js2.put("result","error");
        }
        return js2;
    }
}
