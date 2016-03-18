package org.nutz.lang.util;

/**
 * 带一个参数的通用回调接口
 *
 * @param <T>
 * @author zozoh(zozohtnt@gmail.com)
 */
public interface Callback<T> {

    void invoke(T obj);

}
