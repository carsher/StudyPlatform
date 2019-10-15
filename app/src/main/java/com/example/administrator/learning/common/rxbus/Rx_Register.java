package com.example.administrator.learning.common.rxbus;

/**
 * Created by Administrator on 2018/10/16 0016.
 */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by asdf on 2018/10/16.
 * 自定义注解，作用范围 方法中 用于rxjava 注解扫描回调
 */


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Rx_Register {
}
