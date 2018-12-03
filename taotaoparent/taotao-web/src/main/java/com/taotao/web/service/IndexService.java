package com.taotao.web.service;

import com.taotao.common.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zjj
 * @date 2018/12/3 23:24
 */
@Service
public class IndexService {

    @Autowired
    private ApiService apiService;

    /**
     * 首页大广告
     * @return
     */
    public String getBigAd() {

        // 从后台拿数据，使用httpclient
        return null;
    }
}
