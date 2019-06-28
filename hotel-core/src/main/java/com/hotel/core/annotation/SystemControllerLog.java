package com.hotel.core.annotation;

import java.lang.annotation.*;

/**
 * Title : SystemControllerLog
 * @date 2019-6-27 14:18:26
 * @version v1.0
 * Description: 自定义注解,拦截controller
 */

@Target({ElementType.PARAMETER,ElementType.METHOD}) //作用在参数和方法上
@Retention(RetentionPolicy.RUNTIME) //运行时注解
@Documented //javadoc记录
public @interface SystemControllerLog {
    String description() default "";
}
