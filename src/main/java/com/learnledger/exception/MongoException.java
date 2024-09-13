//package com.learnledger.exception;
//
//import com.mongodb.MongoTimeoutException;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ControllerAdvice
//public class MongoException {
//    
//    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
//    @ExceptionHandler(value = MongoTimeoutException.class)
//    @ResponseBody
//    public String MongoDBException(){
//        return "<script>alert('Mongo timed out exception is occured sorry for the inconvenience');</script>";
//    }
//    
//}
