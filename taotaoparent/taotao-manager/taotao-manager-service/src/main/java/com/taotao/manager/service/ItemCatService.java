package com.taotao.manager.service;

import com.github.abel533.mapper.Mapper;
import com.taotao.common.bean.ItemCatData;
import com.taotao.common.bean.ItemCatResult;
import com.taotao.manager.mapper.ItemCatMapper;
import com.taotao.manager.pojo.BasePojo;
import com.taotao.manager.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 完成对ItemCatResult数据结构的转换
     * @return
     */
    public ItemCatResult queryItemCatWebAll() {
        ItemCatResult result = new ItemCatResult();

        // 查询全部类目
        List<ItemCat> cats = super.queryList(null);

        // 转为Map存储，key为父节点ID，value为数据集合
        Map<Long, List<ItemCat>> itemCatMap = new HashMap<>();
        for (ItemCat cat : cats) {
            if (!itemCatMap.containsKey(cat.getParentId())){
                itemCatMap.put(cat.getParentId(), new ArrayList<ItemCat>());
            }
            itemCatMap.get(cat.getParentId()).add(cat);
        }
        
        // 封装一级对象
        List<ItemCat> itemCatList1 = itemCatMap.get(0L);
        for (ItemCat itemCat : itemCatList1) {
            ItemCatData itemCatData = new ItemCatData();
            itemCatData.setUrl("/products/" + itemCat.getId() + ".html");
            itemCatData.setName("<a href='" + itemCatData.getUrl() + "'>" + itemCat.getName() + "</a>");
            result.getItemList().add(itemCatData);
            if (!itemCat.getParent()){
                continue;
            }

            // 封装二级对象
            List<ItemCat> itemCatList2 = itemCatMap.get(itemCat.getId());
            List<ItemCatData> itemCatData2 = new ArrayList<>();
            itemCatData.setItems(itemCatData2);
            for (ItemCat itemCat2 : itemCatList2) {
                ItemCatData id2 = new ItemCatData();
                id2.setUrl("/products/" + itemCat2.getId() + ".html");
                id2.setName(itemCat2.getName());
                itemCatData2.add(id2);

                // 封装三级对象
                if (itemCat2.getParent()){
                    List<ItemCat> itemCatList3 = itemCatMap.get(itemCat2.getId());
                    List<String> itemCatData3 = new ArrayList<>();
                    id2.setItems(itemCatData3);
                    for (ItemCat itemCat3 : itemCatList3) {
                        String data = "/products/" + itemCat3.getId() + ".html|" + itemCat3.getName();
                        itemCatData3.add(data);
                    }
                }
            }

            // 14是指一级类目的个数
            if (result.getItemList().size() >= 14){
            //if (result.getItemList().size() >= itemCatList1.size()){
                break;
            }
        }
        return result;
    }
}
