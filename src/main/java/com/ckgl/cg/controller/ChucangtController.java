package com.ckgl.cg.controller;

import com.ckgl.cg.bean.Chucangt;
import com.ckgl.cg.service.ChucangtService;
import com.ckgl.cg.util.Response;
import com.ckgl.cg.util.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chucangt")
public class ChucangtController {
    @Autowired
    private ChucangtService chucangtService;

    private static final String SEARCH_BY_KUANHAO = "searchByKuanhao";
    private static final String FIND_BY_KUANHAO = "findByKuanaho";

    private Map<String, Object> query(String searchType, String keyWord, int offset, int limit) {
        Map<String, Object> queryResult = null;

        switch (searchType) {
            case SEARCH_BY_KUANHAO:
                queryResult = chucangtService.selectByKuanhao(keyWord);
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

        List<Chucangt> rows = null;
        long total = 0;

        Map<String, Object> queryResult = query(searchType, keyWord, offset, limit);

        if (queryResult != null) {
            rows = (List<Chucangt>) queryResult.get("data");
            total = (long) queryResult.get("total");
        }

        // 设置 Response
        responseContent.setCustomerInfo("rows", rows);
        responseContent.setResponseTotal(total);
        responseContent.setResponseResult(Response.RESPONSE_RESULT_SUCCESS);
        return responseContent.generateResponse();
    }
}
