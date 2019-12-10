package com.zgc.mtl.config.exception;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zgc.mtl.common.enu.ResultStatusCode;
import com.zgc.mtl.common.util.Result;

/**
 * 全局异常处理
 * @date 2019-12-10 14:29:04
 * @author yang
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	 /**
     * 400 - Bad Request
     */
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, BindException.class,
            ServletRequestBindingException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public Result handleHttpMessageNotReadableException(Exception e) {
    	logger.error(ResultStatusCode.BAD_REQUEST.getMsg(), e);
        return new Result(ResultStatusCode.BAD_REQUEST, null);
    }

    /**
     * 405 - Method Not Allowed
     * 带有@ResponseStatus注解的异常类会被ResponseStatusExceptionResolver 解析
     */
//    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    	logger.error(ResultStatusCode.METHOD_NOT_ALLOWED.getMsg(), e);
        return new Result(ResultStatusCode.METHOD_NOT_ALLOWED, null);
    }
    
	/**
     * 全局异常处理
     * @param e
     * @return
     */
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Result handleException(Throwable e) {
    	logger.error(ResultStatusCode.SYSTEM_ERR.getMsg(), e);
    	//默认internal server error
    	String msg = ResultStatusCode.SYSTEM_ERR.getMsg();
    	if (e instanceof BusinessException) {
    		msg =  e.getMessage();
        }
        return new Result(ResultStatusCode.SYSTEM_ERR.getCode(), msg , null);
    }
}
