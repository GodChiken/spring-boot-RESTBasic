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
            return new ExceptionResponseModel(exception.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()); //번호는 그냥 Test
        }
        if (exception instanceof RestFiledException) {
            return new ExceptionResponseModel(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
        return null;
    }
}
