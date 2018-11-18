package com.taotao.manager.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zjj
 * @date 18-11-1 0:16
 */
@Controller
@RequestMapping("item")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    /**
     * 分页查询商品，按更新时间倒序排序
     *
     * @param page 当前页
     * @param rows 返回行数
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EasyUIResult> queryItemList(@RequestParam("page") Integer page,
                                                      @RequestParam("rows") Integer rows){
        try {
            if (logger.isInfoEnabled()){
                logger.info("分页查询商品, page = {}, rows = {}", page, rows);
            }
            PageInfo<Item> pageInfo = itemService.queryListPage(page, rows);
            EasyUIResult result = new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
            return ResponseEntity.ok(result);
        } catch (Exception e){
            logger.error("分页查询商品出错", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * 新增商品
     *
     * @param item
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(Item item,
                                     @RequestParam("desc") String desc,
                                     @RequestParam("itemParams") String itemParams){
        try {
            int count = itemService.saveItem(item, desc, itemParams);
            if (1 != count){
                logger.error("保存商品失败", item);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
            return ResponseEntity.ok(null);
        }catch (Exception e){
            logger.error("保存商品失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * 编辑商品
     *
     * @param item
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> update(Item item,
                                       @RequestParam("desc") String desc,
                                       @RequestParam("itemParams") String itemParams){
        try {
            int count = itemService.updateItem(item, desc, itemParams);
            if (1 != count){
                logger.error("编辑商品失败", item);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
            return ResponseEntity.ok(null);
        }catch (Exception e){
            logger.error("编辑商品失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
