package com.taotao.common.service;

/**
 * @author zjj
 * @date 2018/12/8 23:07
 * E:代表输入
 * T:代表输出
 */
public interface Function<E, T> {

    /**
     * 输入E，返回T
     * @param e
     * @return
     */
    public T callBack(E e);
}
