package com.taotao.manager.base.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author zjj
 * @date 18-10-28 3:24
 */
public interface TaoTaoMapper<T> {

    @DeleteProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    int deleteByIds(@Param("ids") Object[] key);

    @SelectProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    List<T> select(T record);

    @SelectProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    int selectCount(T record);

    @SelectProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    T selectByPrimaryKey(Object key);

    @InsertProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    int insert(T record);

    @InsertProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    int insertSelective(T record);

    @DeleteProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    int delete(T record);

    @DeleteProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    int deleteByPrimaryKey(Object key);

    @UpdateProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    int updateByPrimaryKey(T record);

    @UpdateProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    int updateByPrimaryKeySelective(T record);

    @SelectProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    int selectCountByExample(Object example);

    @DeleteProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    int deleteByExample(Object example);

    @SelectProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    List<T> selectByExample(Object example);

    @UpdateProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    int updateByExampleSelective(@Param("record") T record, @Param("example") Object example);

    @UpdateProvider(type = TaoTaoMapperProvider.class, method = "dynamicSQL")
    int updateByExample(@Param("record") T record, @Param("example") Object example);

}
