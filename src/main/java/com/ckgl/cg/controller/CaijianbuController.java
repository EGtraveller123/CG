package com.ckgl.cg.controller;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Caijianbu;
import com.ckgl.cg.bean.Caijianbut;
import com.ckgl.cg.service.CaijianbuService;
import com.ckgl.cg.service.CaijianbutService;
import com.ckgl.cg.util.Response;
import com.ckgl.cg.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/caijianbu")
public class CaijianbuController {
    @Autowired
    private CaijianbuService caijianbuService;

    @Autowired
    private CaijianbutService caijianbutService;

    private static final String SEARCH_BY_KUANHAO = "searchByKuanhao";
    private static final String SEARCH_ALL = "searchAll";
    private static final String FIND_BY_KUANHAO_YANSE = "findByKuanhaoYanse";
    private static final String FIND_BY_KUANHAO = "findByKuanhao";
    private static final String SELECT_BY_YEWUBU = "selectByYewubu";

    @RequestMapping("/a")
    public String jumpPage(){
        return "caijianbu";
    }

    private Map<String, Object> query(String searchType,String keyWord,String keyWord2,int offset, int limit) {
        Map<String, Object> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_KUANHAO:
                queryResult = caijianbuService.selectByKuanhao(offset,limit,keyWord);
                break;
            case SEARCH_ALL:
                queryResult = caijianbuService.selectAll(offset,limit);
                break;
            case FIND_BY_KUANHAO_YANSE:
                queryResult = caijianbutService.selectByKuanhaoYanse(offset,limit,keyWord,keyWord2);
                break;
            case FIND_BY_KUANHAO:
                queryResult = caijianbutService.findByKuanhao(offset,limit,keyWord);
                break;
            case SELECT_BY_YEWUBU:
                queryResult = caijianbuService.selectByYewubu(offset,limit,keyWord);
                break;
            default:
                // do other thing
                break;
        }
        return queryResult;
    }

    @RequestMapping(value = "byKuanhao", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> selectByKuanhao(@RequestParam("kuanhao") String kuanhao,
                                        @RequestParam("yanse") String yanse) {
        Response responseContent = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;
        Caijianbut caijianbut = null;
        Map<String, Object> queryResult = query(SEARCH_BY_KUANHAO, kuanhao,yanse, -1, -1);
        if (queryResult != null) {
            caijianbut = (Caijianbut) queryResult.get("data");
            if (caijianbut != null) {
                result = Response.RESPONSE_RESULT_SUCCESS;
            }
        }
        responseContent.setResponseResult(result);
        responseContent.setResponseData(caijianbut);
        return responseContent.generateResponse();
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getCaijianbu(@RequestParam("searchType") String searchType,
                                     @RequestParam("offset") int offset,
                                     @RequestParam("limit") int limit,
                                     @RequestParam("keyWord") String keyWord,
                                     @RequestParam("keyWord2") String keyWord2) {
        Response responseContent = ResponseFactory.newInstance();
        List<Caijianbu> rows = null;
        long total = 0;
        Map<String, Object> queryResult = query(searchType, keyWord,keyWord2,offset, limit);
        if (queryResult != null) {
            rows = (List<Caijianbu>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }
        responseContent.setCustomerInfo("rows", rows);
        responseContent.setResponseTotal(total);
        responseContent.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
        return responseContent.generateResponse();
    }

    @ResponseBody
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public JSONObject insert(@RequestBody JSONObject jsonObject){
        return caijianbutService.insert(jsonObject);
    }

}
