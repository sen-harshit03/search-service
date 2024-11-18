package com.tripsy.searchservice.messaging;

import com.tripsy.common.PackageSearchPayload;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "package-topic", groupId = "myGroup")
    public void consumePackage(PackageSearchPayload payload) {
        try {
            log.info("Consumed payload : {}", payload);
        } catch (SerializationException e) {
            log.error("Deserialization error for message. Invalid message format: {}", payload, e);

        } catch (Exception e) {
            log.error("Error while processing message from topic 'package-topic': {}", payload, e);

        }
    }
}
