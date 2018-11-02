package com.taotao.common.bean;

import java.util.List;

/**
 * @author zjj
 * @date 18-11-1 0:15
 */
public class EasyUIResult {

    private Long tatal;

    private List<?> rows;

    public EasyUIResult() {
    }

    public EasyUIResult(Long tatal, List<?> rows) {
        this.tatal = tatal;
        this.rows = rows;
    }

    public Long getTatal() {
        return tatal;
    }

    public void setTatal(Long tatal) {
        this.tatal = tatal;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
