package com.dream.system;

import com.dream.system.api.ReceiveService;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@ComponentScan("com.dream")
@MapperScan({"com.dream.server.mapper","com.dream.system.dao"})
@EnableBinding(ReceiveService.class)
public class SystemApplication {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }




    private static final Logger LOG = LoggerFactory.getLogger(SystemApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SystemApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("System: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

    @StreamListener("member")
    public void receive(byte[] msg){
        System.out.printf(new String(msg));
    }
}
