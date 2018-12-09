package com.taotao.manager.mapper;

import com.taotao.manager.base.mapper.TaoTaoMapper;
import com.taotao.manager.pojo.Content;

import java.util.List;

/**
 * @author zjj
 * @date 2018/12/2 21:13
 */
public interface ContentMapper extends TaoTaoMapper<Content> {

    /**
     * 按修改时间倒序查询
     * @param content
     * @return
     */
    List<Content> queryListOrderByUpdated(Content content);
}
