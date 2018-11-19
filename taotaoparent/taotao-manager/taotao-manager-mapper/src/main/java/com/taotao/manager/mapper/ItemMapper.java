package com.taotao.manager.mapper;

import com.taotao.manager.base.mapper.TaoTaoMapper;
import com.taotao.manager.pojo.Item;

import java.util.List;

/**
 * @author zjj
 * @date 18-11-1 0:44
 */
public interface ItemMapper extends TaoTaoMapper<Item> {

    /**
     * 根据更新时间倒叙查询
     * @return
     */
    public List<Item> queryItemListOrderByUpdated();
}
