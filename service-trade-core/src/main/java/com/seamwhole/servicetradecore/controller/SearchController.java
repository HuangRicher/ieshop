package com.seamwhole.servicetradecore.controller;

import com.seamwhole.servicetradecore.controller.model.SearchModel;
import com.seamwhole.servicetradecore.model.KeyWords;
import com.seamwhole.servicetradecore.model.SearchHistory;
import com.seamwhole.servicetradecore.service.KeyWordsService;
import com.seamwhole.servicetradecore.service.SearchHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API登录授权
 */
@Api(tags = "商品搜索")
@RestController
@RequestMapping("/api/search")
public class SearchController extends BaseController {
    @Autowired
    private KeyWordsService keyWordsService;
    @Autowired
    private SearchHistoryService searchHistoryService;

    /**
     * 　　index
     */
    @ApiOperation(value = "搜索商品列表")
    @PostMapping("index")
    public Object index(@RequestBody SearchModel searchModel) {
        Map<String, Object> resultObj = new HashMap();
        Map param = new HashMap();
        param.put("is_default", 1);
        param.put("page", 1);
        param.put("limit", 1);
        param.put("sidx", "id");
        param.put("order", "asc");
        List<KeyWords> keywordsEntityList = keyWordsService.queryList(param);
        //取出输入框默认的关键词
        KeyWords defaultKeyword = null != keywordsEntityList && keywordsEntityList.size() > 0 ? keywordsEntityList.get(0) : null;
        //取出热闹关键词
        param = new HashMap();
        param.put("fields", "distinct keyword,is_hot");
        param.put("page", 1);
        param.put("limit", 10);
        param.put("sidx", "id");
        param.put("order", "asc");
        List<Map> hotKeywordList = keyWordsService.hotKeywordList(param);
        //
        param = new HashMap();
        param.put("user_id", searchModel.getUserId());
        param.put("fields", "distinct keyword");
        param.put("page", 1);
        param.put("limit", 10);
        param.put("sidx", "id");
        param.put("order", "asc");
        List<SearchHistory> historyVoList = searchHistoryService.queryList(param);
        String[] historyKeywordList = new String[historyVoList.size()];
        if (null != historyVoList) {
            int i = 0;
            for (SearchHistory historyVo : historyVoList) {
                historyKeywordList[i] = historyVo.getKeyword();
                i++;
            }
        }
        //
        resultObj.put("defaultKeyword", defaultKeyword);
        resultObj.put("historyKeywordList", historyKeywordList);
        resultObj.put("hotKeywordList", hotKeywordList);
        return toResponsSuccess(resultObj);
    }

    /**
     * 　　helper
     */
    @ApiOperation(value = "搜索商品")
    @ApiImplicitParams({@ApiImplicitParam(name = "keyword", value = "关键字", paramType = "path", required = true)})
    @PostMapping("helper")
    public Object helper(@RequestBody SearchModel searchModel) {
        Map param = new HashMap();
        param.put("fields", "distinct keyword");
        param.put("keyword", searchModel.getKeyword());
        param.put("limit", 10);
        param.put("offset", 0);
        List<KeyWords> keywords = keyWordsService.queryList(param);
        String[] keys = new String[keywords.size()];
        if (null != keywords) {
            int i = 0;
            for (KeyWords keywordsVo : keywords) {
                keys[i] = keywordsVo.getKeyword();
                i++;
            }
        }
        //
        return toResponsSuccess(keys);
    }

    /**
     * 　　clearhistory
     */
    @PostMapping("clearhistory")
    public Object clearhistory(@RequestBody SearchModel searchModel) {
        searchHistoryService.deleteByUserId(searchModel.getUserId());
        //
        return toResponsSuccess("");
    }
}
