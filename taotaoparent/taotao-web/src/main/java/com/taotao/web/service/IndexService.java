package com.taotao.web.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.common.service.ApiService;
import com.taotao.manager.pojo.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zjj
 * @date 2018/12/3 23:24
 */
@Service
public class IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexService.class);

    @Autowired
    private ApiService apiService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value("${TAOTAO_MANAGER_URL}")
    private String TAOTAO_MANAGER_URL;

    @Value("${INDEX_BIG_AD}")
    private String INDEX_BIG_AD;

    /**
     * 首页大广告
     * @return
     */
    public String getBigAd() {

        String url = TAOTAO_MANAGER_URL + INDEX_BIG_AD;
        String jsonResult;
        try {
            // 从后台拿数据，使用httpClient
            jsonResult = apiService.doGet(url);
        } catch (Exception e) {
            LOGGER.error("获取大广告失败，url=" + url, e);
            return null;
        }

        EasyUIResult easyUIResult = EasyUIResult.formetToList(jsonResult, Content.class);
        List<Content> contents = (List<Content>) easyUIResult.getRows();

        // 便利集合，封装数据结构
        List<Map<String, Object>> list = new ArrayList<>();
        for (Content content : contents) {
            Map<String, Object> map = new HashMap();
            map.put("src", content.getPic());
            map.put("width", 670);
            map.put("height", 240);
            map.put("srcB", content.getPic2());
            map.put("widthB", 550);
            map.put("heightB", 240);
            map.put("alt", "");
            map.put("href", content.getUrl());

            list.add(map);
        }

        try {
            return MAPPER.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            LOGGER.error("解析出错，list=" + list, e);
            return null;
        }
    }
}
