package com.dream.system.controller;

import com.dream.system.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制层
 *
 * @author Liberica
 * @date 2021-07-22
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;


    @GetMapping("/testUser")
    public void test() throws InterruptedException {
        testService.updateUser();
    }




}
