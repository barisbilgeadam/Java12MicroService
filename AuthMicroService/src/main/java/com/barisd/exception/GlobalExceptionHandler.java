package com.barisd.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMesaj> handleRuntimeException(RuntimeException exception){
        return ResponseEntity.ok(ErrorMesaj.builder()
                        .code(ErrorType.INTERNAL_SERVER_ERROR)
                        .mesaj("Beklenmeyen runtime hatası "+exception.getMessage())
                .build());
    }

    @ExceptionHandler(AuthServiceException.class)
    @ResponseBody
    public ResponseEntity<ErrorMesaj> handleDemoException(AuthServiceException exception){
        ErrorMesaj errorMesaj = createError(exception.getErrorType(), exception);
        errorMesaj.addField(exception.getMessage());
        return ResponseEntity.
                status(exception.getErrorType().getHttpStatus()).
                body(errorMesaj);
    }


    /*
    hata mesajı oluşturmak için metod.
     */
    private  ErrorMesaj createError(ErrorType errorType,Exception ex){
        System.out.println("Hata olustu: "+ex.getMessage());
        return ErrorMesaj.builder()
                .code(errorType)
                .mesaj(errorType.getMesaj())
                .build();
    }

    /*
    global Exceptionlarda fieldslarda ayrı ayrı hataları görmek için
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMesaj> handleAllExceptions(Exception exception) {
        ErrorMesaj errorMesaj = createError(ErrorType.INTERNAL_SERVER_ERROR, exception);
        errorMesaj.addField(exception.getMessage());

        return ResponseEntity
                .status(errorMesaj.getCode().getHttpStatus())
                .body(errorMesaj);
    }
}
