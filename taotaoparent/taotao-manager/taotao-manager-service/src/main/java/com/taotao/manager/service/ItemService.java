package com.taotao.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.common.bean.ItemStatusEnum;
import com.taotao.manager.mapper.ItemMapper;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zjj
 * @date 18-11-1 0:37
 */
@Service
public class ItemService extends BaseService<Item>{

    @Autowired
    ItemMapper itemMapper;

    @Autowired
    ItemDescService itemDescService;

    @Autowired
    ItemParemItemService itemParemItemService;

    public PageInfo<Item> queryListPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return new PageInfo<Item>(itemMapper.queryItemListOrderByUpdated());
    }

    public int saveItem(Item item, String desc, String itemParams) {
        // 初始化数据
        item.setId(null);
        item.setStatus(ItemStatusEnum.NORMAL.getCode());
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());

        // 保存商品、商品描述、商品参数
        int count = 0;
        count = super.save(item);
        count = itemDescService.saveItemDesc(item.getId(), desc);

        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(item.getId());
        itemParamItem.setParamData(itemParams);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(itemParamItem.getCreated());
        count = itemParemItemService.save(itemParamItem);

        return count;
    }

    public int updateItem(Item item, String desc, String itemParams) {
        // 数据限制，不可修改
        item.setCreated(null);

        item.setUpdated(new Date());

        int count = 0;
        count = super.updateSelective(item);
        count = itemDescService.updateItemDesc(item.getId(), desc);
        count = itemParemItemService.updateItemParemItemByItemId(item.getId(), itemParams);

        return count;
    }
}
