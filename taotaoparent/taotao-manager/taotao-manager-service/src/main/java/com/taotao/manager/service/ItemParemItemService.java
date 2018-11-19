package com.taotao.manager.service;

import com.taotao.manager.mapper.ItemParamItemMapper;
import com.taotao.manager.mapper.ItemParamMapper;
import com.taotao.manager.pojo.ItemParam;
import com.taotao.manager.pojo.ItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zjj
 * @date 2018/11/14 23:13
 */
@Service
public class ItemParemItemService extends BaseService<ItemParamItem> {

    @Autowired
    ItemParamItemMapper itemParamItemMapper;

    public int updateItemParemItemByItemId(Long itemId, String itemParams) {
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParams);
        itemParamItem.setCreated(null);
        itemParamItem.setUpdated(new Date());
        int count = itemParamItemMapper.updateItemParemItemByItemId(itemParamItem);
        return count;
    }
}
