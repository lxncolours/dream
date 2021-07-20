package com.dream.business;

import com.dream.business.api.SendService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@ComponentScan("com.dream")
@MapperScan({"com.dream.server.mapper","com.dream.business.dao"})
@EnableBinding(value = SendService.class)
public class BusinessApplication {


    private static final Logger LOG = LoggerFactory.getLogger(BusinessApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BusinessApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("Business地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

    @RequestMapping("/")
    public String getMember(){return "这是会员服务";}

}