package com.tripsy.searchservice.messaging;

import com.tripsy.common.PackageSearchPayload;
import com.tripsy.searchservice.entity.PackageDocument;
import com.tripsy.searchservice.mapper.PackageDocumentMapper;
import com.tripsy.searchservice.service.PackageSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(value = "messaging.service", havingValue = "kafka")
public class KafkaConsumer {

    private final PackageDocumentMapper packageDocumentMapper;
    private final PackageSearchService packageSearchService;

    @KafkaListener(topics = "package-topic", groupId = "myGroup")
    public void consumePackage(final PackageSearchPayload payload) {
        try {
            log.info("Consumed payload : {}", payload);
            final PackageDocument packageDocument = mapToPackageDocument(payload);

            switch (payload.getEventType()) {
                case CREATE, UPDATE -> packageSearchService.upsertDocument(packageDocument);
                case DELETE -> packageSearchService.deleteDocument(packageDocument.getPackageId());
            }

        } catch (SerializationException e) {
            log.error("Deserialization error for message. Invalid message format: {}", payload, e);

        } catch (Exception e) {
            log.error("Error while processing message from topic 'package-topic': {}", payload, e);

        }
    }

    private PackageDocument mapToPackageDocument(final PackageSearchPayload payload) {
        return packageDocumentMapper.toDocument(payload);
    }
}
