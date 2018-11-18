package com.taotao.manager.controller;

import com.taotao.manager.pojo.ItemParam;
import com.taotao.manager.service.ItemParemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
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

    /**
     * 根据商品类目Id查询模板
     *
     * @param itemCatId 商品类目Id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "{itemCatId}", method = RequestMethod.GET)
    public ResponseEntity<ItemParam> queryItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId){
        ItemParam itemParam = new ItemParam();
        itemParam.setItemCatId(itemCatId);
        List<ItemParam> itemParams = itemParemService.queryList(itemParam);
        if (null == itemParams ||itemParams.isEmpty()){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(itemParams.get(0));
    }

    /**
     * 保存模板
     *
     * @param itemCatId 商品类目Id
     * @param paramData 模板JSON字符串
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "{itemCatId}", method = RequestMethod.POST)
    public ResponseEntity<Void> saveItemParam(@PathVariable("itemCatId") Long itemCatId,
                                              @RequestParam("paramData") String paramData){
        try {
            ItemParam itemParam = new ItemParam();
            itemParam.setItemCatId(itemCatId);
            itemParam.setParamData(paramData);
            itemParam.setCreated(new Date());
            itemParam.setUpdated(itemParam.getCreated());
            Integer count = itemParemService.save(itemParam);
            if (count > 0){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
