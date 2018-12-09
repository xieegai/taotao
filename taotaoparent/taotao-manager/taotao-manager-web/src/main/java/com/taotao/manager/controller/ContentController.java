package com.taotao.manager.controller;

import com.github.pagehelper.PageInfo;
import com.taotao.common.bean.Constant;
import com.taotao.common.bean.EasyUIResult;
import com.taotao.common.service.RedisService;
import com.taotao.manager.pojo.Content;
import com.taotao.manager.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author zjj
 * @date 2018/12/2 21:07
 */
@Controller()
@RequestMapping("content")
public class ContentController {


    @Autowired
    private ContentService contentService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<EasyUIResult> queryContentByContentCategoryId(@RequestParam("categoryId") Long categoryId,
                                                                        @RequestParam("page") Integer page,
                                                                        @RequestParam("rows") Integer rows){
        PageInfo<Content> pageInfo = contentService.queryByContentCategory(categoryId, page, rows);
        EasyUIResult result = new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
        return ResponseEntity.ok(result);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> addContent(Content content){
        content.setCreated(new Date());
        content.setUpdated(content.getCreated());

        int result = contentService.save(content);
        if (result == 1){
            delCache();
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateContent(Content content){
        content.setUpdated(new Date());
        int result = contentService.updateSelective(content);

        if (result == 1){
            delCache();
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteContent(@RequestParam("ids") Long[] ids){
        int result = contentService.deletedByIds(ids);

        if (result == ids.length){
            delCache();
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 删除redis缓存的大广告信息
     */
    private void delCache(){
        try {
            redisService.del(Constant.WEB_INDEX_BIG_AD_KEY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
