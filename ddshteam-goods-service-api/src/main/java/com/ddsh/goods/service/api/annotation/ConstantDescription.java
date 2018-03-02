package com.ddsh.goods.service.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 常量描述注解
 * @ClassName: ConstantDescription
 * @author arpgate
 * @date 2018年3月3日 上午12:08:37
 * @version v1.0.0
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
public @interface ConstantDescription {
	String desc();
}
