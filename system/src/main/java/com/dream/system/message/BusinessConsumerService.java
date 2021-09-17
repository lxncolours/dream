package com.dream.system.message;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "producer_business", topic = "topic_business")
public class BusinessConsumerService implements RocketMQListener<String> {
    private static final Logger LOG = LoggerFactory.getLogger(BusinessConsumerService.class);


    @Override
    public void onMessage(String message) {

        LOG.info("=============消费者接受消息===========" + message);

    }
}
