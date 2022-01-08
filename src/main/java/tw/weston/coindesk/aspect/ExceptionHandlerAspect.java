/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 用來攔截所有ExceptionHandler方法，將例外記錄起來
 * @author weston.tan
 */
@Log4j2
@Aspect
@Component
public class ExceptionHandlerAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ExceptionHandler) && args (ex,..)")
    private void execute(Throwable ex) {
    }

    @Before("execute(ex)")
    private void beforeExecuting(Throwable ex) {
        log.warn("An exception thrown while handle the http request.", ex);
    }

}
