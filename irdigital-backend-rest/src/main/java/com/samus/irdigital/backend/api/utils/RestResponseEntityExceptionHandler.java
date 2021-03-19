package com.samus.irdigital.backend.api.utils;

import com.samus.irdigital.backend.api.dto.ErrorDetailMessage;
import com.samus.irdigital.backend.api.dto.RestResponse;
import com.samus.irdigital.backend.api.enums.CodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex, ServletWebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(RestResponse.builder().detail(ErrorDetailMessage.builder().message(
                errors.toString()).step(request.getRequest().getRequestURI()).build()).code(CodeEnum.FAIL).build(),
                HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExcepption(
            Exception ex, ServletWebRequest request) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(RestResponse.builder().detail(ErrorDetailMessage.builder().message(
                ex.getMessage()).step(request.getRequest().getRequestURI()).build()).code(CodeEnum.FAIL).build(),
                HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleExcepption(
            RuntimeException ex, ServletWebRequest request) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(RestResponse.builder().detail(ErrorDetailMessage.builder().message(
                ex.getMessage()).step(request.getRequest().getRequestURI()).build()).code(CodeEnum.FAIL).build(),
                HttpStatus.OK);
    }
}
