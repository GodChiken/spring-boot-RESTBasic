package org.phpbae.advice.rest_advice;

import org.phpbae.advice.ExceptionResponseModel;
import org.phpbae.advice.exception.RestCustomException;
import org.phpbae.advice.exception.RestFiledException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by phpbae on 2017-08-12.
 */
@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ExceptionResponseModel handleNotFoundException(Exception exception) {
        exception.printStackTrace();
        if (exception instanceof RestCustomException) {
            return new ExceptionResponseModel(exception.getMessage(), 900);
        }
        if (exception instanceof RestFiledException) {
            return new ExceptionResponseModel(exception.getMessage(), 901);
        }
        return null;
    }
}
