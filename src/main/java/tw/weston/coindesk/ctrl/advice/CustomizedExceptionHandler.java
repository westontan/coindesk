/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.ctrl.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tw.weston.coindesk.exc.CustomizedException;
import tw.weston.coindesk.vo.ThrowableResponseVo;

/**
 * 客製化例外的處理器
 * @author weston.tan
 */
@RestControllerAdvice
public class CustomizedExceptionHandler {

    @ExceptionHandler(CustomizedException.class)
    ResponseEntity<ThrowableResponseVo> handleCustomizedException(CustomizedException ex) {
        ThrowableResponseVo throwableResponseVo = new ThrowableResponseVo(ex.getResult(), ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(throwableResponseVo);
    }

}
