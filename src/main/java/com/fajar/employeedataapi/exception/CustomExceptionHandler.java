package com.fajar.employeedataapi.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fajar.employeedataapi.model.WebResponse;

import lombok.extern.slf4j.Slf4j;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler 
    public ResponseEntity error(Throwable exception, WebRequest request) {
    	exception.printStackTrace();
    	log.debug("handling error: {}", exception.getMessage());
        WebResponse response = WebResponse.builder().code("400").message(exception.getMessage()).build();
        return buildResponseEntity(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {ApplicationException.class})
    public ResponseEntity applicationException(ApplicationException exception, WebRequest request) {
    	exception.printStackTrace();
    	log.debug("handling error: {}", exception.getMessage());
    	WebResponse response = WebResponse.builder().code("500").message(exception.getMessage()).build();
    	return buildResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity notFoundException(NotFoundException exception, WebRequest request) {
    	exception.printStackTrace();
    	log.debug("handling error: {}", exception.getMessage());
    	WebResponse response = WebResponse.builder().code("404").message(exception.getMessage()).build();
    	return buildResponseEntity(response, HttpStatus.NOT_FOUND);
    }
    
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	 
		return applicationException(new ApplicationException(ex), request);
        // paste custom hadling here
    }
	private ResponseEntity<Object> buildResponseEntity(WebResponse response, HttpStatus status) {
		return new ResponseEntity<Object>(response, status);
	}
}