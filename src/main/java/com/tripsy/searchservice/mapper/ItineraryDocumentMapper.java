package com.tripsy.searchservice.mapper;


import com.tripsy.common.ItineraryPayload;
import com.tripsy.searchservice.entity.ItineraryDocument;
import org.springframework.stereotype.Component;

@Component
public class ItineraryDocumentMapper {

    public ItineraryDocument toDocument(final ItineraryPayload payload) {
        return ItineraryDocument.builder()
                .itineraryId(payload.getItineraryId())
                .day(payload.getDay())
                .activities(payload.getActivities())
                .build();
    }
}
