package com.accenture.accountservice.messaging;

import com.accenture.accountservice.dto.response.AddressResponse;
import com.accenture.accountservice.dto.response.CustomerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@KafkaListener(topics = "${kafka.groupConsumer.topic}")
public class GroupListener {

    @KafkaHandler
    public void getCustomersResponse(@Payload CustomerResponse customerResponse,
                                     @Headers MessageHeaders messageHeaders) {
        log.info("recieved customer = {}", customerResponse);
        log.info("header 1 - {}", messageHeaders.get("X-Custom-Header"));
        log.info("header 2 - {}", messageHeaders.get(KafkaHeaders.RECEIVED_MESSAGE_KEY));
        log.info("header 3 - {}", messageHeaders.get(KafkaHeaders.RECEIVED_TOPIC));
        log.info("header 4 - {}", messageHeaders.get(KafkaHeaders.RECEIVED_PARTITION_ID));
        log.info("header 5 - {}", messageHeaders.get(KafkaHeaders.RECEIVED_TIMESTAMP));
    }

    @KafkaHandler
    public void getAddressResponse(@Payload AddressResponse addressResponse,
                                     @Headers MessageHeaders messageHeaders) {
        log.info("recieved customer = {}", addressResponse);
        log.info("header 1 - {}", messageHeaders.get("X-Custom-Header"));
        log.info("header 2 - {}", messageHeaders.get(KafkaHeaders.RECEIVED_MESSAGE_KEY));
        log.info("header 3 - {}", messageHeaders.get(KafkaHeaders.RECEIVED_TOPIC));
        log.info("header 4 - {}", messageHeaders.get(KafkaHeaders.RECEIVED_PARTITION_ID));
        log.info("header 5 - {}", messageHeaders.get(KafkaHeaders.RECEIVED_TIMESTAMP));
    }
}
