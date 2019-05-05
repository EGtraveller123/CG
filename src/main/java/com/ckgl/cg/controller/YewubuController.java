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
    private static final String FIND_BY_KUANHAO = "findByKuanhao";
    private static final String NONE = "none";

    @RequestMapping("/a")
    public String jumpPage(){
        return "yewubu";
    }


    /**
     * 通用的结果查询方法
     *
     * @param searchType 查询方式
     * @param keyWord    查询关键字
     * @param offset     分页偏移值
     * @param limit      分页大小
     * @return 返回指定条件查询的结果
     */

    private Map<String, Object> query(String searchType, String keyWord, int offset, int limit) {
        Map<String, Object> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_KUANHAO:
//                if (StringUtils.isNumeric(keyWord))
                queryResult = yewubuService.selectByKuanhao(keyWord);
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
            case FIND_BY_KUANHAO:
                queryResult = yewubutService.findByKuanhao(offset, limit,keyWord);
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
    Map<String, Object> getCustomerList(@RequestParam("searchType") String searchType,
                                        @RequestParam("offset") int offset,
                                        @RequestParam("limit") int limit,
                                        @RequestParam("keyWord") String keyWord) {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        List<Yewubu> rows = null;
        long total = 0;

        Map<String, Object> queryResult = query(searchType, keyWord, offset, limit);

        if (queryResult != null) {
            rows = (List<Yewubu>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }

        // 设置 Response
        responseContent.setCustomerInfo("rows", rows);
        responseContent.setResponseTotal(total);
        responseContent.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
        return responseContent.generateResponse();
    }

    /**
     * @param kuanhao 款号
     * @return 返回一个map，其中：key 为 result 的值为操作的结果，包括：success 与 error；key 为 data
     * 的值为客户信息
     */
    @RequestMapping(value = "byKuanhao", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> selectByKuanhao(@RequestParam("kuanhao") String kuanhao) {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;

        // 获取客户信息
        Yewubu yewubu = null;
        Map<String, Object> queryResult = query(SEARCH_BY_KUANHAO, kuanhao, -1, -1);
        if (queryResult != null) {
            yewubu = (Yewubu) queryResult.get("data");
            if (yewubu != null) {
                result = Response.RESPONSE_RESULT_SUCCESS;
            }
        }

        // 设置 Response
        responseContent.setResponseResult(result);
        responseContent.setResponseData(yewubu);
        return responseContent.generateResponse();
    }

    /**
     * @param kuanhao 款号
     * @return 返回一个map，其中：key 为 result 的值为操作的结果，包括：success 与 error；key 为 data
     * 的值为客户信息
     * 这是返回caijianbut表
     */
    @RequestMapping(value = "findByKuanhao", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> findByKuanhao(@RequestParam("keyWord") String kuanhao) {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;

        //初始化caijianbut信息
        Object caijianbut = null;
        Map<String, Object> queryResult = query(FIND_BY_KUANHAO, kuanhao, 5, 0);
        if (queryResult != null) {
            caijianbut = queryResult.get("data");
            if (caijianbut != null) {
                result = Response.RESPONSE_RESULT_SUCCESS;
            }
        }

        // 设置 Response
        responseContent.setResponseResult(result);
        responseContent.setCustomerInfo("rows",caijianbut);
        responseContent.setResponseTotal((Long) queryResult.get("total"));
        return responseContent.generateResponse();
    }



//    /**
//     * @param kehu 客户
//     * @return 返回一个map，其中：key 为 result 的值为操作的结果，包括：success 与 error；key 为 data
//     * 的值为客户信息
//     */
//    @RequestMapping(value = "bykehu", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    Map<String, Object> selectByKehu(@RequestParam("kehu") String kehu) {
//        // 初始化 Response
//        Response responseContent = ResponseFactory.newInstance();
//        String result = Response.RESPONSE_RESULT_ERROR;
//
//        // 获取客户信息
//        Yewubu yewubu = null;
//        Map<String, Object> queryResult = query(SEARCH_BY_KEHU, kehu, -1, -1);
//        if (queryResult != null) {
//            yewubu = (Yewubu) queryResult.get("data");
//            if (yewubu != null) {
//                result = Response.RESPONSE_RESULT_SUCCESS;
//            }
//        }
//
//        // 设置 Response
//        responseContent.setResponseResult(result);
//        responseContent.setResponseData(yewubu);
//
//        return responseContent.generateResponse();
//    }

    @ResponseBody
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public JSONObject insert(@RequestBody JSONObject jsonObject){
        return yewubuService.insert(jsonObject);
    }

    @ResponseBody
    @RequestMapping(value = "/insertYewu",method = RequestMethod.POST)
    public JSONObject insertYewu(@RequestBody JSONObject jsonObject){
        return yewubuService.insertYewu(jsonObject);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject update(@RequestBody JSONObject jsonObject){
        return yewubuService.update(jsonObject);
    }

    @RequestMapping(value = "/sc",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject delete(@RequestParam("kuanhao") String kuanhao) {
        return yewubuService.delete(kuanhao);
    }
}
