package com.taotao.manager.pojo;

import java.util.Date;

/**
 * @author zjj
 * @date 18-10-28 1:06
 */
public abstract class BasePojo {

    private Date created;

    private Date updated;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
