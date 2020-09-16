package com.liu.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.transform.Result;

/**
 * 自定义缓存aop
 * @author
 **/
@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class CustomCacheAspect {
    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.liu.config.AnnotationRedis)") //3
    public void annotationRedisPointCut(){};

    @Before("annotationRedisPointCut()")
    public void show(JoinPoint joinPoint) throws Exception{
        //获取注解
        AnnotationRedis annotationRedis = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(AnnotationRedis.class);
        System.out.println("@Before : "+annotationRedis.key());
    }

    @After("annotationRedisPointCut()")
    public void show2(JoinPoint joinPoint) throws Exception{
        //获取注解
        AnnotationRedis annotationRedis = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(AnnotationRedis.class);

        System.out.println("@After : "+annotationRedis.key());
    }


    /**
     * 织入 returning 获取response 的值
     * 请求完拦截返回数据
     * 更新缓存
     */
    @AfterReturning(returning = "object", pointcut = "annotationRedisPointCut()")
    public Object doAfterReturning(JoinPoint joinPoint,Object object) {
    /*ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();
    HttpServletResponse response = attributes.getResponse();*/
        //获取注解
        AnnotationRedis customCache = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(AnnotationRedis.class);

        log.info("请求并执行完操作后拦截返回数据");
        log.info("response={}",object);
        if (object instanceof Result) {
            Result rest = (Result) object;
            /*if(CommonConstant.SC_OK_200.equals(rest.getCode())){
                log.info("缓存数据");
                log.info("{}={}={}",customCache.key(),customCache.timeOut(),rest.getResult());
                this.redisUtil.set(customCache.key(),rest.getResult(),customCache.timeOut());
                rest.setPid("织入的值");
            }*/
        }

        return object;
    }

}
