/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.weston.coindesk.ctrl.advice;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import tw.weston.coindesk.vo.MultipleErrorsResponseVo;
import tw.weston.coindesk.vo.ThrowableResponseVo;

/**
 * SpringMVC相關的例外處理器
 * @author weston.tan
 */
@RestControllerAdvice
public class SpringMvcExceptionHandler {
    
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ThrowableResponseVo handleUnexpectedError(Throwable ex) {
        return new ThrowableResponseVo(ex);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    ThrowableResponseVo handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        return new ThrowableResponseVo("UNSUPPORTED_HTTP_METHOD", "The request handler does not support " + request.getMethod() + " method.");
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    ThrowableResponseVo handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
        return new ThrowableResponseVo("UNSUPPORTED_MEDIA_TYPE", "The request handler does not support " + request.getContentType() + " type.");
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    ThrowableResponseVo handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex) {
        return new ThrowableResponseVo("UNACCEPTABLE_MEDIA_TYPE", "The request handler cannot generate a response that is acceptable by the client.");
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ThrowableResponseVo handleMissingPathVariable(MissingPathVariableException ex) {
        return new ThrowableResponseVo("MISSING_PATH_PARAMETER", "The URI template does not match the @PathVariable name " + ex.getVariableName() + " declared on the method parameter.");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ThrowableResponseVo handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        return new ThrowableResponseVo("MISSING_URL_PARAMETER", "There is a missing url parameter " + ex.getParameterName() + " with " + ex.getParameterType() + " type.");
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ThrowableResponseVo handleServletRequestBindingError(ServletRequestBindingException ex) {
        return new ThrowableResponseVo("REQUEST_BINDING_ERROR", "Fatal binding exception. For more details, please refer to the log files.");
    }

    @ExceptionHandler(ConversionNotSupportedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ThrowableResponseVo handleConversionNotSupported(ConversionNotSupportedException ex) {
        return new ThrowableResponseVo("UNSUPPORTED_CONVERSION", "No suitable editor or converter can be found for a bean property.");
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ThrowableResponseVo handleTypeMismatch(TypeMismatchException ex) {
        return new ThrowableResponseVo("MISMATCH_TYPE", "There is a type mismatch (" + ex.getValue() + " is not a " + ex.getRequiredType() + " value) while trying to set the bean property " + ex.getPropertyName() + ".");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ThrowableResponseVo handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        return new ThrowableResponseVo("UNREADABLE_HTTP_MESSAGE", "The request body cannot be read as " + request.getContentType() + " type.");
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ThrowableResponseVo handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpServletResponse response) {
        return new ThrowableResponseVo("UNWRITABLE_HTTP_MESSAGE", "The response body cannot be writen as " + response.getContentType() + " type.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    MultipleErrorsResponseVo handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Stream<String> objectErrors = ex.getBindingResult().getGlobalErrors().stream().map(objectError -> {
            return objectError.getObjectName() + ": " + objectError.getDefaultMessage();
        });
        Stream<String> fieldErrors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
            return fieldError.getObjectName() + "." + fieldError.getField() + ": " + fieldError.getDefaultMessage();
        });
        return new MultipleErrorsResponseVo("INVALID_REQUEST_PARAMETER", Stream.concat(objectErrors, fieldErrors).collect(Collectors.toList()));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ThrowableResponseVo handleMissingServletRequestPart(MissingServletRequestPartException ex) {
        return new ThrowableResponseVo("MISSING_REQUEST_PART", "The request is not a multipart/form-data one or the part " + ex.getRequestPartName() + " is not present in it.");
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    MultipleErrorsResponseVo handleBindError(BindException ex) {
        return new MultipleErrorsResponseVo("BINDING_ERROR", ex.getFieldErrors().stream().map(fieldError -> {
            return fieldError.getField() + ": " + fieldError.getDefaultMessage();
        }).collect(Collectors.toList()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ThrowableResponseVo handleNoHandlerFound(NoHandlerFoundException ex) {
        return new ThrowableResponseVo("HANDLER_NOT_FOUND", "There is no handler found for the request.");
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    ThrowableResponseVo handleAsyncRequestTimeout(AsyncRequestTimeoutException ex) {
        return new ThrowableResponseVo("REQUEST_TIMEOUT", "The request reached timeout.");
    }
    
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ThrowableResponseVo handleMaxUploadSizeExceeded(MaxUploadSizeExceededException ex) {
        return new ThrowableResponseVo("MAX_FILE_SIZE_EXCEEDED", "The size of a uploaded file exceeded the maximum size.");
    }
    
}
