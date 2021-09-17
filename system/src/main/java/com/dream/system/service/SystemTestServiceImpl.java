package com.dream.system.service;

import com.dream.api.api.SystemTestService;
import com.dream.server.domain.User;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class SystemTestServiceImpl implements SystemTestService {
    @Override
    public String systemTest(User user) {
        System.out.println(user);
        return "success";
    }
}
