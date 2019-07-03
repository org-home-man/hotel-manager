package com.hotel.core.annotation;

import java.lang.annotation.*;

/**
 * Title : SystemServiceLog
 * @date 2019-6-27 14:18:26
 * @version v1.0
 * Description: 自定义注解,拦截service
 */

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {
    String description() default "";
}
