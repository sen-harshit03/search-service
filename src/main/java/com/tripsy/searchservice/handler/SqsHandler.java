package com.tripsy.searchservice.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.tripsy.common.PackageSearchPayload;
import com.tripsy.searchservice.entity.PackageDocument;
import com.tripsy.searchservice.mapper.PackageDocumentMapper;
import com.tripsy.searchservice.service.PackageSearchService;
import com.tripsy.searchservice.util.Serializer;
import io.awspring.cloud.sqs.annotation.SqsListener;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "messaging.service", havingValue = "sqs")
public class SqsHandler {

    private final Serializer serializer;
    private final PackageSearchService packageSearchService;
    private final PackageDocumentMapper packageDocumentMapper;


    @SqsListener("${AWS_QUEUE}")
    @Async
    public void receiveMessage(@Payload String message) {
        try {
            PackageSearchPayload payload = deserializePayload(message);
            processPayload(payload);
        } catch (Exception e) {
            log.error("Error while processing message from the queue", e);

        }

    }

    private PackageSearchPayload deserializePayload(final String message) {
        JsonNode rootNode = serializer.readTree(message);
        String actualMessage = rootNode.get("Message").asText();
        log.info("Received message from the SQS: {}", message);

        return serializer.readValueFromString(actualMessage, PackageSearchPayload.class);
    }

    private void processPayload(final PackageSearchPayload payload) {
        PackageDocument packageDocument = packageDocumentMapper.toDocument(payload);
        switch (payload.getEventType()) {
            case CREATE, UPDATE -> packageSearchService.upsertDocument(packageDocument);
            case DELETE -> packageSearchService.deleteDocument(packageDocument.getPackageId());
        }
    }

    @PostConstruct
    public void init() {
        log.info("SQS Listener initialized for search service.");
    }
}
