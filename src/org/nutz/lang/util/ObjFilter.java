package org.nutz.lang.util;

/**
 * 通用过滤器
 *
 * @param <T>
 * @author zozoh(zozohtnt@gmail.com)
 */
public interface ObjFilter<T> {

    boolean accept(T o);

}
