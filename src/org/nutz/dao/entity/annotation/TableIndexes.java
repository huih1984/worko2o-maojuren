package org.nutz.dao.entity.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明一组数据表的索引
 *
 * @author zozoh(zozohtnt@gmail.com)
 * @see Index
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface TableIndexes {

    Index[] value();

}
