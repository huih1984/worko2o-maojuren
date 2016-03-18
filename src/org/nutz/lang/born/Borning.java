package org.nutz.lang.born;

/**
 * 对象抽象创建方式
 *
 * @param <T>
 * @author zozoh(zozohtnt@gmail.com)
 */
public interface Borning<T> {

    T born(Object... args);

}
