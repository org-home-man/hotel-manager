package com.hotel.admin.advice;

import com.hotel.core.exception.GlobalException;
import com.hotel.core.http.HttpResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ResponseBody
    @ExceptionHandler
    public HttpResult globalExceptionHandler(GlobalException gblException){
        return HttpResult.error(gblException.getCode(),gblException.getMsg());
    }
}
