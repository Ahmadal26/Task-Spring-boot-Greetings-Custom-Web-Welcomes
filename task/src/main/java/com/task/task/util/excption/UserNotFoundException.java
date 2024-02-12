package com.task.task.util.excption;


public class UserNotFoundException extends RuntimeException{

    public  UserNotFoundException(String str){
        super(str);
    }
}