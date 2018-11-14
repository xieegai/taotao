package com.taotao.manager.controller;

import com.taotao.manager.pojo.ItemParam;
import com.taotao.manager.service.ItemParemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

/**
 * @author zjj
 * @date 2018/11/14 23:14
 */
@Controller
@RequestMapping("item/param")
public class ItemParamController {

    @Autowired
    private ItemParemService itemParemService;

    @ResponseBody
    @RequestMapping(value = "{itemCatId}", method = RequestMethod.GET)
    public ResponseEntity<ItemParam> queryItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId){
        ItemParam itemParam = new ItemParam();
        itemParam.setItemCatId(itemCatId);
        List<ItemParam> itemParams = itemParemService.queryList(itemParam);
        if (null == itemParams ||itemParams.isEmpty()){

        }
    }
}
