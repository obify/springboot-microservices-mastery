package com.mycompany.locationmanagementapi.service;

import com.mycompany.locationmanagementapi.converter.UserConverter;
import com.mycompany.locationmanagementapi.entity.UserEntity;
import com.mycompany.locationmanagementapi.exception.BusinessException;
import com.mycompany.locationmanagementapi.exception.ErrorModel;
import com.mycompany.locationmanagementapi.model.UserModel;
import com.mycompany.locationmanagementapi.repository.UserEntityRepository;
import com.mycompany.locationmanagementapi.validation.UserValidator;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserEntityRepository entityRepository;

    @Mock
    private UserConverter userConverter;

    @Test
    public void test_login_error(){

        UserModel userModel = new UserModel();
        List<ErrorModel> errorModelList = new ArrayList<>();

        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode("invalid_email");
        errorModel.setMessage("invalid email");
        errorModelList.add(errorModel);

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);

        Assertions.assertThrows(BusinessException.class, ()->{
            userService.login(userModel);
        });
    }

    @Test
    public void test_login_with_wrong_credentials() {

        UserModel userModel = new UserModel();
        userModel.setEmail("abc@g.com");
        userModel.setPassword("xyz123");
        List<ErrorModel> errorModelList = new ArrayList<>();

        UserEntity userEntity = null;

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(entityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword())).thenReturn(userEntity);

        Assertions.assertThrows(BusinessException.class, ()->{
            userService.login(userModel);
        });
    }

    @Test
    public void test_login_with_correct_credentials() throws BusinessException {

        UserModel userModel = new UserModel();
        userModel.setEmail("abc1@g.com");
        userModel.setPassword("xyz1231");

        List<ErrorModel> errorModelList = new ArrayList<>();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("abc1@g.com");

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);

        Mockito.when(entityRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword())).thenReturn(userEntity);
        boolean result = userService.login(userModel);

        Assertions.assertTrue(result);
    }

    @Test
    public void test_register_error(){

        UserModel userModel = new UserModel();
        List<ErrorModel> errorModelList = new ArrayList<>();

        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode("invalid_email");
        errorModel.setMessage("invalid email");
        errorModelList.add(errorModel);

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);

        Assertions.assertThrows(BusinessException.class, ()->{
            userService.register(userModel);
        });
    }

    @Test
    public void test_register_with_existing_user() {

        UserModel userModel = new UserModel();
        userModel.setEmail("abc@g.com");
        userModel.setPassword("xyz123");
        List<ErrorModel> errorModelList = new ArrayList<>();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("abc@g.com");

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(entityRepository.findByEmail(userModel.getEmail())).thenReturn(userEntity);

        Assertions.assertThrows(BusinessException.class, ()->{
            userService.register(userModel);
        });
    }

    @Test
    @DisplayName("Testing new user registration flow")
    public void test_register_with_new_user() throws BusinessException {

        UserModel userModel = new UserModel();
        userModel.setEmail("abc@g.com");
        userModel.setPassword("xyz123");

        List<ErrorModel> errorModelList = new ArrayList<>();

        UserEntity userEntity = null;

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setEmail("abc@g.com");
        userEntity1.setPassword("xyz123");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("abc@g.com");
        userEntity2.setPassword("xyz123");
        userEntity2.setId(11L);

        Mockito.when(userValidator.validateRequest(userModel)).thenReturn(errorModelList);
        Mockito.when(entityRepository.findByEmail(userModel.getEmail())).thenReturn(userEntity);

        Mockito.when(userConverter.convertModelToEntity(userModel)).thenReturn(userEntity1);
        Mockito.when(entityRepository.save(userEntity1)).thenReturn(userEntity2);

        Long userId = userService.register(userModel);

        Assertions.assertEquals(11L, userId);
    }
}
