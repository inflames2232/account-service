package com.accenture.accountservice.messaging;

import com.accenture.accountservice.dto.response.AddressResponse;
import com.accenture.accountservice.dto.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class KafkaMessageProducerImpl implements MessageProducer {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Value("${app.topic.accounts}")
    private String topicAccounts;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void sendCustomerMessage(CustomerResponse data) {
        Message<CustomerResponse>  message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topicAccounts)
                .setHeader(KafkaHeaders.MESSAGE_KEY, applicationName + data.getClass().getName())
                .setHeader(KafkaHeaders.PARTITION_ID, 0)
                .setHeader("X-Custom-Header", "Custom Header")
                .build();

        kafkaTemplate.send(message);
    }

    @Override
    public void sendAddressMessage(AddressResponse data) {
        Message<AddressResponse>  message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topicAccounts)
                .setHeader(KafkaHeaders.MESSAGE_KEY, applicationName + data.getClass().getName())
                .setHeader(KafkaHeaders.PARTITION_ID, 0)
                .setHeader("X-Custom-Header", "AddressResponse Header")
                .build();

        kafkaTemplate.send(message);
    }
}
