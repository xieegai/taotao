package com.taotao.manager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.common.bean.ItemCatResult;
import com.taotao.manager.pojo.ItemCat;
import com.taotao.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

    private static final ObjectMapper mapper = new ObjectMapper();

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
     * 有回调函数，单独处理
     *
     * 对前台web系统返回多级类目
     * @param callback 回调函数名
     */
    /*@ResponseBody
    @RequestMapping(value = "web/all", method = RequestMethod.GET,produces= "application/json;charset=UTF-8")
    public String queryItemCatWebAll(@RequestParam("callback") String callback) throws IOException {
        ItemCatResult result = itemCatService.queryItemCatWebAll();

        // 以对象转换为JSON字符串
        return callback + "(" + mapper.writeValueAsString(result) + ");";
    }*/

    /**
     * 有回调函数，统一处理，扩展了ResponseBody注解
     * 处理类 见： com.taotao.common.spring.extend.converter.CallbackMappingJackson2HttpMessageConverter
     * 配置  见： taotao-manager-servlet.xml
     *
     * 对前台web系统返回多级类目
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "web/all", method = RequestMethod.GET)
    public ItemCatResult queryItemCatWebAll() {
       return itemCatService.queryItemCatWebAll();
    }
}
