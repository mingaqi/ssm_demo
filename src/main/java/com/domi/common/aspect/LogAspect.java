package com.domi.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
            * try{
     *      @Before前置通知
     *      method.invoke();
     *      @AfterRunning返回通知
     * }catch(e){
     *      @AfterThrowing：异常通知，
     * }
     * @After
     *
             * 告诉Spring这些放在都在那个方法的哪个位置执行
     * 1）、告诉位置
     [1]@Before：前置通知，在方法执行之前执行
     [2]@After：后置通知，在方法执行最终结束之后执行。
    如果没异常
     [3]@AfterRunning：返回通知，在方法返回结果之后执行
     [4]@AfterThrowing：异常通知，在方法抛出异常之后执行


    1、编写切入点表达式，来告诉spring是切入哪个方法的这个位置
     */

    @Pointcut(value = "execution(* com..service.*.*(..))")
    public void myPoint() {
    }

    /*前置通知：目标方法执行之前执行以下方法体的内容*/
    @Before(value="myPoint()")
    public void beforeMethod(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        Object[] arys = jp.getArgs();
        logger.debug("AOP log begin for bean ["+jp.getTarget().getClass()+"]");
        logger.debug("[前置通知]: the method["+methodName+"] begins with "+ Arrays.asList(arys));

    }

    /*返回通知：目标方法正常执行完毕时执行以下代码*/
    @AfterReturning(value="myPoint()", returning = "result")
    public void afterReturningMethod(JoinPoint jp, Object result){
        logger.debug("[返回通知]: the method["+jp.getSignature().getName()+"] ends with ["+result+" ]");
    }

    /**
     * 后置通知：目标方法执行之后执行以下方法体的内容，不管是否发生异常。
     * @param jp
     */
    @After(value="myPoint()")
    public void afterMethod(JoinPoint jp){
        logger.debug("【后置通知】this is a afterMethod advice...");
    }

    /**
     * 异常通知：目标方法发生异常的时候执行以下代码
     */
    @AfterThrowing(value="myPoint()",throwing="e")
    public void afterThorwingMethod(JoinPoint jp, NullPointerException e){
        String methodName = jp.getSignature().getName();
        logger.debug("【异常通知】the method 【" + methodName + "】 occurs exception: " + e);
    }

/*      *//**
       * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
       * @return
       *//*
      @Around(value="myPoint()")
      public Object aroundMethod(ProceedingJoinPoint jp){
          String methodName = jp.getSignature().getName();
          Object result = null;
          try {
              System.out.println("【环绕通知中的--->前置通知】：the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
              //执行目标方法
              result = jp.proceed();
              System.out.println("【环绕通知中的--->返回通知】：the method 【" + methodName + "】 ends with " + result);
          } catch (Throwable e) {
              System.out.println("【环绕通知中的--->异常通知】：the method 【" + methodName + "】 occurs exception " + e);
          }

          System.out.println("【环绕通知中的--->后置通知】：-----------------end.----------------------");
          return result;
      }*/

}
