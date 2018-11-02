package com.taotao.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.common.bean.ItemStatusEnum;
import com.taotao.manager.mapper.ItemMapper;
import com.taotao.manager.pojo.Item;
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

    public PageInfo<Item> queryListPage(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return new PageInfo<Item>(itemMapper.queryItemListOrderByUpdated());
    }

    public int saveItem(Item item, String desc) {
        // 初始化数据
        item.setId(null);
        item.setStatus(ItemStatusEnum.NORMAL.getCode());
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());

        // 保存商品和商品描述
        int count = 0;
        count = super.save(item);
        count = itemDescService.saveItemDesc(item.getId(), desc);

        return count;
    }
}
