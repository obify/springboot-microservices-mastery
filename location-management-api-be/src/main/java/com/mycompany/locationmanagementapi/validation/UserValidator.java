package com.mycompany.locationmanagementapi.validation;

import com.mycompany.locationmanagementapi.constant.ErrorType;
import com.mycompany.locationmanagementapi.exception.ErrorModel;
import com.mycompany.locationmanagementapi.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public List<ErrorModel> validateRequest(UserModel userModel){

        List<ErrorModel> errorModelList = new ArrayList<>();

        if(null != userModel && userModel.getEmail() == null){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("Email cannot be empty");

            errorModelList.add(errorModel);
        }

        if(null != userModel && userModel.getPassword() == null){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("Password cannot be empty");

            errorModelList.add(errorModel);
        }
        return errorModelList;
    }
}
