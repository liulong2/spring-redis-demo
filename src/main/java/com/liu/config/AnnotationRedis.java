package com.liu.config;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnotationRedis {
    /**
     * 设置缓存key
     * @return
     */
    String key() default RedisConstant.TEST_DEMO_CACHE;
    /**
     * 超时时间
     * @return
     */
    long timeOut() default RedisConstant.TEST_OUT_TIME;

}

