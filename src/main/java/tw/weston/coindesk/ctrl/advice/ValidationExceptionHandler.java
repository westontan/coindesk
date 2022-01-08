/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.ctrl.advice;

import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tw.weston.coindesk.vo.MultipleErrorsResponseVo;

/**
 * Bean驗證相關的例外處理器
 * @author weston.tan
 */
@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    MultipleErrorsResponseVo handleConstraintViolation(ConstraintViolationException ex) {
        return new MultipleErrorsResponseVo("INVALID_REQUEST_PARAMETER", ex.getConstraintViolations().stream().map(constraintViolation -> {
            return constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage();
        }).collect(Collectors.toList()));
    }

}
