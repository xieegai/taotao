package com.taotao.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.pojo.Content;
import org.springframework.stereotype.Service;

/**
 * @author zjj
 * @date 2018/12/2 21:14
 */
@Service
public class ContentService extends BaseService<Content> {
    public PageInfo<Content> queryByContentCategory(Long categoryId, Integer page, Integer rows) {

        PageHelper.startPage(page, rows);

        Content content = new Content();
        content.setCategoryId(categoryId);

        return new PageInfo<Content>(super.queryList(content));
    }
}
