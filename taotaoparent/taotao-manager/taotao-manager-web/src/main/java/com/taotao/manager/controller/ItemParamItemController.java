package com.taotao.manager.controller;

import com.taotao.manager.pojo.ItemParam;
import com.taotao.manager.pojo.ItemParamItem;
import com.taotao.manager.service.ItemParemItemService;
import com.taotao.manager.service.ItemParemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author zjj
 * @date 2018/11/14 23:14
 */
@Controller
@RequestMapping("item/param/item")
public class ItemParamItemController {

    @Autowired
    private ItemParemItemService itemParemItemService;

    @ResponseBody
    @RequestMapping(value = "{itemId}")
    public ResponseEntity<ItemParamItem> queryItemParamItemByItemId(@PathVariable("itemId") Long itemId){
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(itemId);
        List<ItemParamItem> itemParamItems = itemParemItemService.queryList(itemParamItem);
        if (null == itemParamItems || itemParamItems.isEmpty()){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(itemParamItems.get(0));
    }

}
