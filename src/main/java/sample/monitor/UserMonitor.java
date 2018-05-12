package sample.monitor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * aop切面
 * Create By Xie ZuoZhi On 2018/5/10
 */
@Aspect
@Component
public class UserMonitor {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserMonitor.class);
    @AfterReturning(value = "execution(* sample.controller.*.*(..))", returning = "result")
    public void afterReturn( JoinPoint jp, Object result ) {
        System.out.println("切面：" + jp + "，结果：" + result);
    }
    @AfterThrowing(value = "execution(* sample.controller.*.*(..))", throwing = "e")
    public void afterThrow(JoinPoint jp, Exception e) {
        LOGGER.error("报错" + jp, e);
    }
    @AfterThrowing(value = "execution(* sample.controller.*.*(..))", throwing = "e")
    public void afterThrow(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> set = e.getConstraintViolations();
        for (ConstraintViolation violation: set) {
            LOGGER.error(violation.getMessage());
        }
    }

}
