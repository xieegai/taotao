package com.taotao.manager.service;

import com.github.abel533.mapper.Mapper;
import com.taotao.manager.mapper.ItemCatMapper;
import com.taotao.manager.pojo.BasePojo;
import com.taotao.manager.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjj
 * @date 18-10-28 2:29
 */
@Service
public class ItemCatService extends BaseService<ItemCat>{

    /*@Autowired
    private ItemCatMapper itemCatMapper;
    @Override
    public Mapper<ItemCat> getMapper() {
        return itemCatMapper;
    }*/

    public List<ItemCat> queryItemCatList(Long parentId) {
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(parentId);
        return super.queryList(itemCat);
    }
}
