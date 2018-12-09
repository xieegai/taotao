package com.taotao.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.mapper.ContentMapper;
import com.taotao.manager.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjj
 * @date 2018/12/2 21:14
 */
@Service
public class ContentService extends BaseService<Content> {

    @Autowired
    private ContentMapper contentMapper;

    public PageInfo<Content> queryByContentCategory(Long categoryId, Integer page, Integer rows) {

        PageHelper.startPage(page, rows);

        Content content = new Content();
        content.setCategoryId(categoryId);

        List<Content> list = contentMapper.queryListOrderByUpdated(content);
        return new PageInfo(list);
        //return new PageInfo(super.queryList(content));
    }
}
