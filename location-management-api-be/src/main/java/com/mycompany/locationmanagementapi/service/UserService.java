package com.mycompany.locationmanagementapi.service;

import com.mycompany.locationmanagementapi.exception.BusinessException;
import com.mycompany.locationmanagementapi.model.UserModel;

public interface UserService {
    public boolean login(UserModel userModel) throws BusinessException;
    public Long register(UserModel userModel) throws BusinessException;
}
