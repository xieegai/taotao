package com.taotao.common.bean;

/**
 * @author zjj
 * @date 18-11-2 0:21
 */
public enum ItemStatusEnum {

    NORMAL("正常", 1),
    OFFSHALVE("下架", 2),
    DELETED("删除", 3);

    private String decs;
    private Integer code;

    public String getDecs() {
        return decs;
    }

    public Integer getCode() {
        return code;
    }

    private ItemStatusEnum(String desc, Integer code){
        this.decs = desc;
        this.code = code;
    }
}
