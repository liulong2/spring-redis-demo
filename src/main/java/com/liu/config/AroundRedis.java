package com.liu.config;

import io.netty.util.internal.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Aspect
public class AroundRedis {

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.liu.config.AnnotationRedis)") //3
    public void annotationRedisPointCut(){};

    @Around("annotationRedisPointCut()")
    public Object doAround (ProceedingJoinPoint point) throws Throwable{
        Object object;
        AnnotationRedis customCache = ((MethodSignature)point.getSignature()).getMethod().getAnnotation(AnnotationRedis.class);
        String key = customCache.key();
        if (StringUtil.isNullOrEmpty(customCache.key())){
            key = RedisConstant.USER_CACHE_2;
            System.out.println("Around : key为空 : " + key);
        }
        System.out.println("Around : object " + key);
        if (!redisUtil.hasKey(key)){
            try {
                // 实际在这里调用了方法
                Object result = point.proceed();
                System.out.println("新建缓存 : "+result);
                this.redisUtil.set(key,result,customCache.timeOut());

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        object = redisUtil.get(key);
        return object;

    }

}
