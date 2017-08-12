package org.phpbae.advice.rest_advice;

import org.phpbae.advice.ExceptionResponseModel;
import org.phpbae.advice.exception.RestCustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by phpbae on 2017-08-12.
 */
@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(RestCustomException.class)
    public ExceptionResponseModel handleNotFoundException(RestCustomException exception) {
        ExceptionResponseModel exceptionResponseModel = new ExceptionResponseModel(exception.getMessage());
        return exceptionResponseModel;
    }
}
