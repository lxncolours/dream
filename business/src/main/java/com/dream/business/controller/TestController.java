package com.dream.business.controller;

import com.dream.api.api.SystemTestService;
import com.dream.business.api.ISendService;
import com.dream.server.domain.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @DubboReference(check = false)
    private SystemTestService systemTestService;

    @Autowired
    private ISendService sendService;



    @RequestMapping("/test")
    public String test() {
        User user = new User();
        user.setLoginName("dlcocksure");
        user.setName("测试");
        user.setUserId(1);
        return systemTestService.systemTest(user);
    }

    @RequestMapping("/test-mq")
    public String testMq() {
        LOG.info("========进入mq测试方法=========");
        sendService.hello("测试");
        return "send message success";
    }
}
