package com.vantec.warehouse.inventorystocks.exception;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), LocalDateTime.now(), req.getDescription(false), null);
        return ResponseEntity.badRequest().body(errorDetails);
    }
	 
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

    	ErrorDetails errorDetails = new ErrorDetails();
        var errors = new HashMap<>();
        for (var err : ex.getBindingResult().getAllErrors()) 
            errors.put(((FieldError) err).getField(), err.getDefaultMessage());
        
        errorDetails.setFieldError(errors);
        errorDetails.setLocalDate(LocalDateTime.now());
        errorDetails.setDecsription(request.getDescription(false));
        return this.handleExceptionInternal(ex, errorDetails, headers, status, request);
    }
}