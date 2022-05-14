package com.taisiia.shop.controller;

import com.taisiia.shop.domain.dto.ErrorDto;
import com.taisiia.shop.domain.dto.FieldErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//do obsługi wszystkich wyjątków w aplikacji
@RestControllerAdvice
//daje zmienną log
@Slf4j
public class AdviceController {
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFoundException(EntityNotFoundException entityNotFoundException) {
        log.error("Not found entity", entityNotFoundException);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public void handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
        log.error("Dublicate email address", sqlIntegrityConstraintViolationException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<FieldErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error("Incorrect input data", methodArgumentNotValidException);
        return methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    FieldError fieldError = (FieldError) error;
                    return new FieldErrorDto(fieldError.getField(), fieldError.getDefaultMessage());
                })
                .collect(Collectors.toList());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
       log.error(constraintViolationException.getLocalizedMessage(),constraintViolationException);
        Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
//        constraintViolations.stream()
//                .map(constraintViolation -> new ErrorDto(constraintViolation.getMessageTemplate())
        return new ErrorDto(constraintViolationException.getLocalizedMessage());

    }
}
