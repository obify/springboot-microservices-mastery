package com.mycompany.locationmanagementapi.exception;

import lombok.Data;

import java.util.List;

@Data
public class BusinessException extends Exception {

    private final transient List<ErrorModel> errorList;

    public BusinessException(List<ErrorModel> errorList){
        this.errorList = errorList;
    }
}
