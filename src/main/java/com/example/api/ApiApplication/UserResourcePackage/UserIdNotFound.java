package com.example.api.ApiApplication.UserResourcePackage;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class UserIdNotFound extends RuntimeException {
public   UserIdNotFound(String message){
    super(message);
}
}
