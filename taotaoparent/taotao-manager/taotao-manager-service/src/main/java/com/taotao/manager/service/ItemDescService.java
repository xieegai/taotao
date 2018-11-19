package com.taotao.manager.service;

import com.taotao.manager.pojo.ItemDesc;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zjj
 * @date 18-11-2 0:08
 */
@Service
public class ItemDescService extends BaseService<ItemDesc> {

    public int saveItemDesc(Long id, String desc) {
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(itemDesc.getCreated());
        return super.save(itemDesc);
    }

    public int updateItemDesc(Long id, String desc) {
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(null);
        itemDesc.setUpdated(new Date());
        return super.updateSelective(itemDesc);
    }
}
