package com.taotao.manager.pojo;

import javax.persistence.*;

/**
 * @author zjj
 * @date 2018/11/26 23:13
 */
@Table(name = "tb_content_category")
public class ContentCategory extends BasePojo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    private String name;
    private Integer status;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "is_parent")
    private Boolean isParent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    // 扩展字段，用于easyui的tree结构
    public String getText(){
        return getName();
    }

    // 扩展字段，用于easyui的tree结构
    public String getState(){
        return getParent() ? "closed" : "open";
    }
}
