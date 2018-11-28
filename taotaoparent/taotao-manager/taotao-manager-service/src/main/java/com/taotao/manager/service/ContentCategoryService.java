package com.taotao.manager.service;

import com.taotao.manager.pojo.ContentCategory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public int deleteContentCategory(Long parentId, Long id) {

        // 递归删除当前类目及其所有子类目
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        ids = getAllChildren(id, ids);
        Long[] longs = ids.toArray(new Long[]{});
        int result = super.deletedByIds(longs);

        // 如果父节点下没有其他子节点，则修改父节点isParent为false
        ContentCategory param = new ContentCategory();
        param.setParentId(parentId);
        List<ContentCategory> others = super.queryList(param);
        if (null == others || others.isEmpty()){
            ContentCategory parent = new ContentCategory();
            parent.setId(parentId);
            parent.setParent(false);
            parent.setUpdated(new Date());
            result = super.updateSelective(parent);
        }

        return  result;
    }

    private List<Long> getAllChildren(Long id, List<Long> ids) {
        ContentCategory param = new ContentCategory();
        param.setParentId(id);
        List<ContentCategory> children = super.queryList(param);
        for (ContentCategory child : children) {
            ids.add(child.getId());
            getAllChildren(child.getId(), ids);
        }

        return ids;
    }
}
