package com.dream.business.service;

import com.dream.business.api.ISendService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SendServiceImpl implements ISendService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void hello(String name) {
        Message<String> message = MessageBuilder.withPayload(name).build();
        rocketMQTemplate.sendMessageInTransaction("topic_business",message,null);
    }
}
