package com.taotao.common.service;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * 调用外部服务的工具类
 * @author zjj
 * @date 2018/12/3 23:27
 */
@Service
public class ApiService {

    /**
     * 创建HttpClient对象
     */
    @Autowired(required = false)
    private CloseableHttpClient httpClient;

    /**
     * 创建连接配置
     */
    @Autowired(required = false)
    private RequestConfig requestConfig;

    public String doGet(String url) throws IOException, URISyntaxException {
        return doGet(url, null);
    }

    /**
     * 执行Get请求
     * @param url
     * @param params 请求参数
     * @return
     */
    public String doGet(String url, Map<String, Object> params) throws URISyntaxException, IOException {

        // 定义请求的参数
        URI uri = null;
        if (params != null && !params.isEmpty()){
            URIBuilder uriBuilder = new URIBuilder(url);
            for (Map.Entry<String, Object> entity : params.entrySet()) {
                uriBuilder.setParameter(entity.getKey(), String.valueOf(entity.getValue()));
            }
            uri = uriBuilder.build();
        }

        // 创建http GET请求
        HttpGet httpGet = null;
        if (null != uri){
            httpGet = new HttpGet(uri);
        } else {
            httpGet = new HttpGet(url);
        }

        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response = null;

        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
        }

        return null;
    }
}
