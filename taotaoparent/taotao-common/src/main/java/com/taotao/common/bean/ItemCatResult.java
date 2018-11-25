package com.taotao.common.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 多级类目返回结果
 * @author zjj
 * @date 2018/11/23 22:37
 */
public class ItemCatResult {

    @JsonProperty("data") //序列化后json的属性名称
    private List<ItemCatData> itemList = new ArrayList<ItemCatData>();

    public List<ItemCatData> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemCatData> itemList) {
        this.itemList = itemList;
    }
}
