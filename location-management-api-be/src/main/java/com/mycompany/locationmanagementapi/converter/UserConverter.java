package com.mycompany.locationmanagementapi.converter;

import com.mycompany.locationmanagementapi.entity.UserEntity;
import com.mycompany.locationmanagementapi.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertModelToEntity(UserModel userModel){

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userModel.getEmail());
        userEntity.setFullName(userModel.getFullName());
        userEntity.setMobileNumber(userModel.getMobileNumber());
        userEntity.setPassword(userModel.getPassword());

        return userEntity;
    }
}
