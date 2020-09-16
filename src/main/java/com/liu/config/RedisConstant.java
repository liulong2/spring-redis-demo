package com.liu.config;

import io.netty.util.internal.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;

public class RedisConstant {
    /**
     * 默认测试缓存key
     * */
    public static final String TEST_DEMO_CACHE = "test:demo";
    /**

     /**
     * 缓存超时时间
     * */
    public static final long TEST_OUT_TIME = 900;



    /**
     * 常用缓存地址
     * */
    public static final String USER_CACHE_1 = "user:cache1";
    public static final String USER_CACHE_2 = "user:cache2";
    public static final String USER_CACHE_3 = "user:cache3";
    public static final String USER_CACHE_4 = "user:cache4";
    public static final String USER_CACHE_5 = "user:cache5";
    public static final String USER_CACHE_6 = "user:cache6";
    public static final String USER_CACHE_7 = "user:cache7";
    public static final String USER_CACHE_8 = "user:cache8";

}
