package com.dream.system.service;

import com.dream.server.exception.BusinessException;
import com.dream.server.exception.BusinessExceptionCode;
import com.dream.system.api.IUserService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private IUserService userService;


    @GlobalTransactional
    public void updateUser() throws InterruptedException {

        userService.updateUser();

        Thread.sleep(30*1000);

        throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);

    }


}
