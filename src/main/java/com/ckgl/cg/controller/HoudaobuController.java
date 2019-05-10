package com.ckgl.cg.controller;

import com.alibaba.fastjson.JSONObject;
import com.ckgl.cg.bean.Houdaobu;
import com.ckgl.cg.bean.Houdaobut;
import com.ckgl.cg.service.HoudaobuService;
import com.ckgl.cg.service.HoudaobutService;
import com.ckgl.cg.util.Response;
import com.ckgl.cg.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/houdaobu")
public class HoudaobuController {
    @Autowired
    private HoudaobuService houdaobuService;

    @Autowired
    private HoudaobutService houdaobutService;

    private static final String SEARCH_BY_KUANHAO = "searchByKuanhao";
    private static final String SEARCH_ALL = "searchAll";
    private static final String FIND_BY_KUANHAO = "findByKuanhao";
    private static final String SELECT_BY_YEWUBU = "selectByYewubu";

    @RequestMapping("/a")
    public String jumpPage(){
        return "houdaobu";
    }

    private Map<String, Object> query(String searchType, String keyWord, int offset, int limit) {
        Map<String, Object> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_KUANHAO:
                queryResult = houdaobuService.selectByKuanhao(offset,limit,keyWord);
                break;
            case SEARCH_ALL:
                queryResult = houdaobuService.selectAll(offset,limit);
                break;
            case FIND_BY_KUANHAO:
                queryResult = houdaobutService.findById(offset,limit,keyWord);
                break;
            case SELECT_BY_YEWUBU:
                queryResult = houdaobuService.selectByCaijianbu(offset,limit,keyWord);
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
    Map<String, Object> selectByKuanhao(@RequestParam("kuanhao") String kuanhao) {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();
        String result = Response.RESPONSE_RESULT_ERROR;

        // 获取后道部
        Houdaobu houdaobu = null;
        Map<String, Object> queryResult = query(SEARCH_BY_KUANHAO, kuanhao, -1, -1);
        if (queryResult != null) {
            houdaobu = (Houdaobu) queryResult.get("data");
            if (houdaobu != null) {
                result = Response.RESPONSE_RESULT_SUCCESS;
            }
        }
        // 设置 Response
        responseContent.setResponseResult(result);
        responseContent.setResponseData(houdaobu);
        return responseContent.generateResponse();
    }

//    /**
//     * @param kuanhao 款号
//     * @return 返回一个map，其中：key 为 result 的值为操作的结果，包括：success 与 error；key 为 data
//     * 的值为客户信息
//     * 这是返回houdaobut表
//     */
//    @RequestMapping(value = "findByKuanhao", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    Map<String, Object> findByKuanhao(@RequestParam("keyWord") String kuanhao) {
//        // 初始化 Response
//        Response responseContent = ResponseFactory.newInstance();
//        String result = Response.RESPONSE_RESULT_ERROR;
//
//        //初始化houdaobut信息
//        Object houdaobut = null;
//        Map<String, Object> queryResult = query(FIND_BY_KUANHAO, kuanhao, 5, 0);
//        if (queryResult != null) {
//            houdaobut = queryResult.get("data");
//            if (houdaobut != null) {
//                result = Response.RESPONSE_RESULT_SUCCESS;
//            }
//        }
//
//        // 设置 Response
//        responseContent.setResponseResult(result);
//        responseContent.setCustomerInfo("rows",houdaobut);
//        responseContent.setResponseTotal((Long) queryResult.get("total"));
//        return responseContent.generateResponse();
//    }

    /**
     * @param searchType 搜索类型
     * @param offset     如有多条记录时分页的偏移值
     * @param limit      如有多条记录时分页的大小
     * @param keyWord    搜索的关键字
     * @return 返回查询的结果，其中键值为 rows 的代表查询到的每一记录，若有分页则为分页大小的记录；键值为 total 代表查询到的符合要求的记录总条数
     */
    @RequestMapping(value = "all", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getHoudaobuList(@RequestParam("searchType") String searchType,
                                        @RequestParam("offset") int offset,
                                        @RequestParam("limit") int limit,
                                        @RequestParam("keyWord") String keyWord) {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();
        List<Houdaobu> rows = null;
        long total = 0;
        Map<String, Object> queryResult = query(searchType, keyWord, offset, limit);
        if (queryResult != null) {
            rows = (List<Houdaobu>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }
        // 设置 Response
        responseContent.setCustomerInfo("rows", rows);
        responseContent.setResponseTotal(total);
        responseContent.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
        return responseContent.generateResponse();
    }

    @ResponseBody
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public JSONObject insert(@RequestBody JSONObject jsonObject){
        return houdaobutService.insert(jsonObject);
    }
}
