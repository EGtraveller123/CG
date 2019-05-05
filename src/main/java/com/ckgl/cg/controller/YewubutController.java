package com.ckgl.cg.controller;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Yewubu;
import com.ckgl.cg.bean.Yewubut;
import com.ckgl.cg.service.YewubutService;
import com.ckgl.cg.util.Response;
import com.ckgl.cg.util.ResponseFactory;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/yewubut")
public class YewubutController {
    @Autowired
    private YewubutService yewubutService;

    private static final String SEARCH_BY_KUANHAO = "searchByKuanhao";
    private static final String FIND_BY_KUANHAO = "findByKuanaho";

    private Map<String, Object> query(String searchType, String keyWord, int offset, int limit) {
        Map<String, Object> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_KUANHAO:
                queryResult = yewubutService.selectByKuanhao(keyWord);
                break;
//            case FIND_BY_KUANHAO:
//                queryResult = caijianbutService.findByKuanhao(offset, limit,keyWord);
            default:
                // do other thing
                break;
        }
        return queryResult;
    }

    @ResponseBody
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public JSONObject insert(@RequestBody JSONObject jsonObject){
        return yewubutService.insert(jsonObject);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public JSONObject delete(@RequestBody JSONObject jsonObject){
        return yewubutService.delete(jsonObject);
    }



    @RequestMapping(value = "findByKuanhao", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getYewubuList(@RequestParam("searchType") String searchType,
                                      @RequestParam("offset") int offset,
                                      @RequestParam("limit") int limit,
                                      @RequestParam("keyWord") String keyWord) {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        List<Yewubut> rows = null;
        long total = 0;

        Map<String, Object> queryResult = query(searchType, keyWord, offset, limit);

        if (queryResult != null) {
            rows = (List<Yewubut>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }

        // 设置 Response
        responseContent.setCustomerInfo("rows", rows);
        responseContent.setResponseTotal(total);
        responseContent.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
        return responseContent.generateResponse();
    }
}
