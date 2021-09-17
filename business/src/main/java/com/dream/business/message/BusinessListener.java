package com.dream.business.message;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

/**
 * 业务侦听器
 *
 * @author Liberica
 * @date 2021-07-28
 * consumerGroup = "producer_business", topic = "topic_business"
 */
@RocketMQTransactionListener
public class BusinessListener implements RocketMQLocalTransactionListener {

    private static final Logger LOG = LoggerFactory.getLogger(BusinessListener.class);

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        LOG.info("================成功接受事务消息后调用=============");

        return RocketMQLocalTransactionState.COMMIT;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        LOG.info("================MQ查询本地事务提交状态===========");
        return RocketMQLocalTransactionState.COMMIT;
    }
}
