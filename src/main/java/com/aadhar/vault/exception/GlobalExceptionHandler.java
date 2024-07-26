package com.aadhar.vault.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aadhar.vault.payload.ResponseMessage;

@RestControllerAdvice
public class GlobalExceptionHandler {
	private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
    @ExceptionHandler(GenericException.class)
    public ResponseMessage handleGenericException(
        HttpServletRequest httpServletRequest,
        GenericException ex,
        HttpServletResponse httpServletResponse
    ) {
        httpServletResponse.setStatus(ex.getCode().value());
        log.debug("erorr {0}",ex);
        return new ResponseMessage<>(ex.getCode().value(), ex.getMessage(), ex.getCode());
    }

//    @ExceptionHandler(MailAuthenticationException.class)
//    public ResponseMessage handleMailAuthenticationException(
//        HttpServletRequest httpServletRequest,
//        MailAuthenticationException ex,
//        HttpServletResponse httpServletResponse
//    ) {
//    	log.debug("erorr {0}",ex);
//        return new ResponseMessage<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseMessage<HttpStatus> handleValidationException(
//        HttpServletRequest httpServletRequest,
//        MethodArgumentNotValidException ex,
//        HttpServletResponse httpServletResponse
//    ) {
//    	log.debug("erorr {0}",ex);
//        httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//        return new ResponseMessage<>(HttpStatus.BAD_REQUEST.value(), ex.getFieldError() != null ? ex.getFieldError().getDefaultMessage() : ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
}
