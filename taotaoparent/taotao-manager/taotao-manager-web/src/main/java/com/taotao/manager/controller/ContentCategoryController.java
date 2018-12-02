package com.taotao.manager.controller;

import com.taotao.manager.pojo.ContentCategory;
import com.taotao.manager.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author zjj
 * @date 2018/11/26 23:17
 */
@Controller
@RequestMapping("content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<ContentCategory> queryContentCategoryList(@RequestParam(value = "id", defaultValue = "0") Long parentId){
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setParentId(parentId);
        return contentCategoryService.queryList(contentCategory);
    }

    /**
     * 新增内容类目
     * @param contentCategory
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ContentCategory> saveContentCategory(ContentCategory contentCategory){
        int result = contentCategoryService.saveContentCategory(contentCategory);

        if (result != 1){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(contentCategory);
    }

    /**
     * 重命名
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateContentCategory(@RequestParam("id") Long id,
                                                      @RequestParam("name") String name){
        ContentCategory contentCategory = new ContentCategory();
        contentCategory.setId(id);
        contentCategory.setName(name);
        contentCategory.setUpdated(new Date());
        int result = contentCategoryService.updateSelective(contentCategory);

        if (result != 1){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 删除类目
     * @param parentId
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteContentCategory(@RequestParam("parentId") Long parentId,
                                                      @RequestParam("id") Long id){
        int result = contentCategoryService.deleteContentCategory(parentId, id);

        if (result < 1){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    


}
