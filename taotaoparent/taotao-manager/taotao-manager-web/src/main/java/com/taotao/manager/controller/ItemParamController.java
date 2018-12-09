package com.taotao.manager.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manager.pojo.ItemParam;
import com.taotao.manager.service.ItemParemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<EasyUIResult> queryList(@RequestParam("page") Integer page,
                                  @RequestParam("rows") Integer rows){
        PageHelper.startPage(page, rows);

        List<ItemParam> itemParams = itemParemService.queryList(null);

        PageInfo pageInfo = new PageInfo(itemParams);

        return ResponseEntity.ok(new EasyUIResult(pageInfo.getTotal(), pageInfo.getList()));
    }


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

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> delete(@RequestParam("ids") Long[] ids){
        int result = itemParemService.deletedByIds(ids);

        if (result == ids.length){
            Map<String, Object> map = new HashMap<>();
            map.put("statusCode", 200);
            return ResponseEntity.ok().body(map);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}
