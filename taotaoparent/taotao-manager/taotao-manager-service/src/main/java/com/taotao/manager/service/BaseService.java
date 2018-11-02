package com.taotao.manager.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manager.base.mapper.TaoTaoMapper;
import com.taotao.manager.pojo.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zjj
 * @date 18-10-28 0:41
 */
public abstract class BaseService<T extends BasePojo> {

    /**
     * 需要子类实现返回具体的Mapper
     * @return
     */
    /*public abstract Mapper<T> getMapper();*/

    @Autowired
    private TaoTaoMapper<T> mapper;

    public TaoTaoMapper<T> getMapper(){
        return mapper;
    }

    public T queryById(Long id){
        return getMapper().selectByPrimaryKey(id);
    }

    public List<T> queryList(T t){
        return getMapper().select(t);
    }

    public PageInfo<T> queryListPage(T t, Integer page, Integer rows){
        // 设置分页
        PageHelper.startPage(page, rows);
        // 查询
        List<T> list = this.queryList(t);
        return new PageInfo<T>(list);
    }

    public Integer queryCount(T t){
        return getMapper().selectCount(t);
    }

    public Integer save(T t){
        return getMapper().insert(t);
    }

    public Integer saveSelective(T t){
        return getMapper().insertSelective(t);
    }

    public Integer update(T t){
        return getMapper().updateByPrimaryKey(t);
    }

    public Integer updateSelective(T t){
        return getMapper().updateByPrimaryKeySelective(t);
    }

    public Integer deleteById(Long id){
        return getMapper().deleteByPrimaryKey(id);
    }

    public Integer deletedByIds(Long[] ids){
        return getMapper().deleteByIds(ids);
    }

}
