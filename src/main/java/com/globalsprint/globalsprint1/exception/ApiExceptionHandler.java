package com.globalsprint.globalsprint1.exception;

import com.globalsprint.globalsprint1.payload.enums.ResponseCode;
import com.globalsprint.globalsprint1.payload.response.APIResponse;
import com.globalsprint.globalsprint1.payload.response.BaseErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final BaseErrorResponse baseErrorResponse;
    private static List<String> getValidationMessage(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(ApiExceptionHandler::getValidationMessage)
                .collect(Collectors.toList());
    }
    private static String getValidationMessage(ObjectError error) {
        if (error instanceof FieldError fieldError) {
            String className = fieldError.getObjectName();
            String property = fieldError.getField();
            Object invalidValue = fieldError.getRejectedValue();
            String message = fieldError.getDefaultMessage();
            return String.format("%s.%s %s, but it was %s", className, property, message, invalidValue);
        }
        return String.format("%s: %s", error.getObjectName(), error.getDefaultMessage());
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public APIResponse handleBadRequestException(BadRequestException e){
        return baseErrorResponse.buildErrorResponse(e.getCode(),e.getMessage(),e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse handleJavaxValidationException(MethodArgumentNotValidException e) {
        System.out.println("e = " + e);
        System.out.println("Entered here");
        String errorMessage = getValidationMessage(e.getBindingResult()).toString();
        return baseErrorResponse.buildErrorResponse(ResponseCode.BAD_REQUEST.getCode(), errorMessage, e);
    }
}
