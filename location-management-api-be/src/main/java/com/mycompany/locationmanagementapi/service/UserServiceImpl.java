package com.mycompany.locationmanagementapi.service;

import com.mycompany.locationmanagementapi.constant.ErrorType;
import com.mycompany.locationmanagementapi.converter.UserConverter;
import com.mycompany.locationmanagementapi.entity.UserEntity;
import com.mycompany.locationmanagementapi.exception.BusinessException;
import com.mycompany.locationmanagementapi.exception.ErrorModel;
import com.mycompany.locationmanagementapi.model.UserModel;
import com.mycompany.locationmanagementapi.repository.UserEntityRepository;
import com.mycompany.locationmanagementapi.validation.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserEntityRepository entityRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserValidator userValidator;

    @Override
    public boolean login(UserModel userModel) throws BusinessException {
        logger.debug("Entering method login");
        //empty check of email and password
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if(!CollectionUtils.isEmpty(errorModelList)){
            throw new BusinessException(errorModelList);
        }

        boolean result = false;
        UserEntity userEntity = entityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());

        if(userEntity == null){

            List<ErrorModel> errorList = new ArrayList<>();

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.AUTH_INVALID_CREDENTIALS.toString());
            errorModel.setMessage("Incorrect email or password");

            errorList.add(errorModel);
            logger.warn("Invalid login attempt");
            throw new BusinessException(errorList);
        }else {
            result = true;
            logger.info("Login was success");
        }
        logger.debug("Exiting method login");
        return result;
    }

    @Override
    public Long register(UserModel userModel) throws BusinessException {

        //empty check of email and password
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if(!CollectionUtils.isEmpty(errorModelList)){
            throw new BusinessException(errorModelList);
        }

        //check if user already exist
        UserEntity ue = entityRepository.findByEmail(userModel.getEmail());

        if(null != ue){

            List<ErrorModel> errorList = new ArrayList<>();

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.ALREADY_EXIST.toString());
            errorModel.setMessage("User with this email already exist, try another email");

            errorList.add(errorModel);
            throw new BusinessException(errorList);
        }

        UserEntity userEntity = userConverter.convertModelToEntity(userModel);
        UserEntity userEntity1 = entityRepository.save(userEntity);

        return userEntity1.getId();
    }
}
