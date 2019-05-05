package com.ckgl.cg.controller;


import com.ckgl.cg.bean.Caijianbut;
import com.ckgl.cg.service.CaijianbutService;
import com.ckgl.cg.util.Response;
import com.ckgl.cg.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/caijianbut")
public class CaijianbutController {
    @Autowired
    private CaijianbutService caijianbutService;

    private static final String SEARCH_BY_KUANHAO = "searchByKuanhao";
    private static final String FIND_BY_KUANHAO = "findByKuanaho";

    private Map<String, Object> query(String searchType, String keyWord, int offset, int limit) {
        Map<String, Object> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_KUANHAO:
                queryResult = caijianbutService.selectByKuanhao(keyWord);
                break;
//            case FIND_BY_KUANHAO:
//                queryResult = caijianbutService.findByKuanhao(offset, limit,keyWord);
            default:
                // do other thing
                break;
        }
        return queryResult;
    }

    @RequestMapping(value = "findByKuanhao", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getCaijianbutList(@RequestParam("searchType") String searchType,
                                          @RequestParam("offset") int offset,
                                          @RequestParam("limit") int limit,
                                          @RequestParam("keyWord") String keyWord) {
        // 初始化 Response
        Response responseContent = ResponseFactory.newInstance();

        List<Caijianbut> rows = null;
        long total = 0;

        Map<String, Object> queryResult = query(searchType, keyWord, offset, limit);

        if (queryResult != null) {
            rows = (List<Caijianbut>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }

        // 设置 Response
        responseContent.setCustomerInfo("rows", rows);
        responseContent.setResponseTotal(total);
        responseContent.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
        return responseContent.generateResponse();
    }

//    /**
//     * @param kuanhao 款号
//     * @return 返回一个map，其中：key 为 result 的值为操作的结果，包括：success 与 error；key 为 data
//     * 的值为客户信息
//     */
//    @RequestMapping(value = "byKuanhao", method = RequestMethod.GET)
//    public
//    @ResponseBody
//    Map<String, Object> findByKuanhao(@RequestParam("kuanhao") String kuanhao) {
//        // 初始化 Response
//        Response responseContent = ResponseFactory.newInstance();
//        String result = Response.RESPONSE_RESULT_ERROR;
//
//        // 获取客户信息
//        Caijianbut caijianbut = null;
//        Map<String, Object> queryResult = query(FIND_BY_KUANHAO, kuanhao, -1, -1);
//        if (queryResult != null) {
//            caijianbut = (Caijianbut) queryResult.get("data");
//            if (caijianbut != null) {
//                result = Response.RESPONSE_RESULT_SUCCESS;
//            }
//        }
//
//        // 设置 Response
//        responseContent.setResponseResult(result);
//        responseContent.setResponseData(caijianbut);
//        return responseContent.generateResponse();
//    }

}
