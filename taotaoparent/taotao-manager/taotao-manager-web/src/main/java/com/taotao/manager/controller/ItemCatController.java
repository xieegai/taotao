package com.taotao.manager.controller;

import com.taotao.common.bean.ItemCatResult;
import com.taotao.manager.pojo.ItemCat;
import com.taotao.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zjj
 * @date 18-10-28 2:24
 */
@RequestMapping("item/cat")
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 查询类目列表
     *
     * @param parentId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ItemCat> queryItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId){
        return itemCatService.queryItemCatList(parentId);
    }

    /**
     * 对前台web系统返回多级类目
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "web/all", method = RequestMethod.GET)
    public ItemCatResult queryItemCatWebAll(){
        return itemCatService.queryItemCatWebAll();
    }
}
