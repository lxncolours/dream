package com.dream.business.api;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface SendService {

    @Output("member")
    SubscribableChannel sendMember();
}
