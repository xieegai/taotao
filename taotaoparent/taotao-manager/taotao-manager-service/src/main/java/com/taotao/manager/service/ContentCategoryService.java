package com.taotao.manager.service;

import com.taotao.manager.pojo.ContentCategory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zjj
 * @date 2018/11/26 23:16
 */
@Service
public class ContentCategoryService extends BaseService<ContentCategory> {

    public int saveContentCategory(ContentCategory contentCategory) {
        // 保存当前类目信息
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(contentCategory.getCreated());
        contentCategory.setParent(false);
        contentCategory.setSortOrder(1);
        contentCategory.setStatus(1);

        int result = super.save(contentCategory);

        // 更改父类目isParent状态，如果父类目isParent状态为false则改为true
        ContentCategory parentCat = super.queryById(contentCategory.getParentId());
        if (!parentCat.getParent()){
            parentCat.setUpdated(new Date());
            parentCat.setParent(true);
            result = super.updateSelective(parentCat);
        }

        return result;
    }
}
