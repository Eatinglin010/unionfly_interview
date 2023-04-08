package com.unionfly.interview.handler;

import com.mongodb.MongoWriteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<String> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("MethodArgumentNotValidException:"+exception.getMessage());
    }

    @ExceptionHandler(MongoWriteException.class)
    public  ResponseEntity<String> mongoWriteExceptionHandle(MongoWriteException exception){
        if(exception.getCode() == 11000) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("此email已被註冊");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
