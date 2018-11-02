package com.taotao.manager.mapper;

import com.taotao.manager.base.mapper.TaoTaoMapper;
import com.taotao.manager.pojo.Item;

import java.util.List;

/**
 * @author zjj
 * @date 18-11-1 0:44
 */
public interface ItemMapper extends TaoTaoMapper<Item> {

    public List<Item> queryItemListOrderByUpdated();
}
