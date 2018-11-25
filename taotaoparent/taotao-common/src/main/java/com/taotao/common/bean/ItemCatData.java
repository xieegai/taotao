package com.taotao.common.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author zjj
 * @date 2018/11/23 22:38
 */
public class ItemCatData {

    @JsonProperty("u") //序列化json后数据为u
    private String url;

    @JsonProperty("n")
    private String name;

    @JsonProperty("i")
    private List<?> items;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}
