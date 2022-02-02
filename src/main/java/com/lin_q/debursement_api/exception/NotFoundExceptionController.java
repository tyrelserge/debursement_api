package com.lin_q.debursement_api.exception;

import java.util.Date;

import com.lin_q.debursement_api.Constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
@ControllerAdvice
public class NotFoundExceptionController {

     // handling specific exception
     @ExceptionHandler(value = ResourceNotFoundException.class)
     public ResponseEntity<?> exception(ResourceNotFoundException exception, WebRequest request){
         NotFoundExceptionMessage exceptionMessage = 
         new NotFoundExceptionMessage(Constants.ERROR, 
         new Date(), exception.getMessage(), request.getDescription(false));
         return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
     }
 
     // handling global exception
     @ExceptionHandler(Exception.class)
     public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
          NotFoundExceptionMessage exceptionMessage = 
          new NotFoundExceptionMessage(Constants.ERROR, 
          new Date(), exception.getMessage(), request.getDescription(false));
          return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
     }
    
}