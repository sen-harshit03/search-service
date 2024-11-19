package com.tripsy.searchservice.mapper;

import com.tripsy.common.DestinationPayload;
import com.tripsy.searchservice.entity.DestinationDocument;
import org.springframework.stereotype.Component;

@Component
public class DestinationDocumentMapper {

    public DestinationDocument toDocument(final DestinationPayload payload) {
        return DestinationDocument.builder()
                .destinationId(payload.getDestinationId())
                .destinationName(payload.getDestinationName())
                .country(payload.getCountry())
                .build();
    }
}
