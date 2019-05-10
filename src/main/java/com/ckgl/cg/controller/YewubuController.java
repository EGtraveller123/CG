package com.ckgl.cg.controller;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Yewubu;
import com.ckgl.cg.service.YewubuService;
import com.ckgl.cg.service.YewubutService;
import com.ckgl.cg.util.Response;
import com.ckgl.cg.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/yewubu")
public class YewubuController{

    @Autowired
    private YewubuService yewubuService;

    @Autowired
    private YewubutService yewubutService;

    private static final String SEARCH_BY_KUANHAO = "searchByKuanhao";
    private static final String SEARCH_BY_KEHU = "searchByKehu";
    private static final String SEARCH_ALL = "searchAll";
    private static final String NONE = "none";

    @RequestMapping("/a")
    public String jumpPage(){
        return "yewubu";
    }

    private Map<String, Object> query(String searchType, String keyWord, int offset, int limit) {
        Map<String, Object> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_KUANHAO:
//                if (StringUtils.isNumeric(keyWord))
                queryResult = yewubuService.selectByKuanhao(offset,limit,keyWord);
                break;
            case SEARCH_BY_KEHU:
                queryResult = yewubuService.selectByKehu(offset, limit, keyWord);
                break;
            case SEARCH_ALL:
                queryResult = yewubuService.selectAll(offset, limit);
                break;
            case NONE:
                queryResult = yewubuService.selectAll(offset, limit);
                break;
            default:
                // do other thing
                break;
        }
        return queryResult;
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getYewubuList(@RequestParam("searchType") String searchType,
                                      @RequestParam("offset") int offset,
                                      @RequestParam("limit") int limit,
                                      @RequestParam("keyWord") String keyWord) {
        Response responseContent = ResponseFactory.newInstance();
        List<Yewubu> rows = null;
        long total = 0;
        Map<String, Object> queryResult = query(searchType, keyWord, offset, limit);
        if (queryResult != null) {
            rows = (List<Yewubu>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }
        responseContent.setCustomerInfo("rows", rows);
        responseContent.setResponseTotal(total);
        responseContent.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
        return responseContent.generateResponse();
    }

    @RequestMapping(value = "byKuanhao", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> selectByKuanhao(@RequestParam("kuanhao") String kuanhao) {
        Response responseContent = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;
        Yewubu yewubu = null;
        Map<String, Object> queryResult = query(SEARCH_BY_KUANHAO, kuanhao, -1, -1);
        if (queryResult != null) {
            yewubu = (Yewubu) queryResult.get("data");
            if (yewubu != null) {
                result = Response.RESPONSE_RESULT_SUCCESS;
            }
        }
        responseContent.setResponseResult(result);
        responseContent.setResponseData(yewubu);
        return responseContent.generateResponse();
    }

    @RequestMapping(value = "byKehu", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> selectBykehu(@RequestParam("keyWord") String kehu) {
        Response responseContent = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;
        Object caijianbut = null;
        Map<String, Object> queryResult = query(SEARCH_BY_KEHU, kehu, 5, 0);
        if (queryResult != null) {
            caijianbut = queryResult.get("data");
            if (caijianbut != null) {
                result = Response.RESPONSE_RESULT_SUCCESS;
            }
        }
        responseContent.setResponseResult(result);
        responseContent.setCustomerInfo("rows",caijianbut);
        responseContent.setResponseTotal((Long) queryResult.get("total"));
        return responseContent.generateResponse();
    }

    @ResponseBody
    @RequestMapping(value = "insertYewu",method = RequestMethod.POST)
    public JSONObject insert(@RequestBody JSONObject jsonObject){
        return yewubutService.insert(jsonObject);
    }


    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public JSONObject delete(@RequestBody JSONObject jsonObject){
        return yewubutService.deleteYewubut(jsonObject);
    }
}
