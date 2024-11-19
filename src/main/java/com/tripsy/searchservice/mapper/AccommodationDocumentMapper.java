package com.tripsy.searchservice.mapper;

import com.tripsy.common.AccommodationPayload;
import com.tripsy.searchservice.entity.AccommodationDocument;
import org.springframework.stereotype.Component;

@Component
public class AccommodationDocumentMapper {

    public AccommodationDocument toDocument(final AccommodationPayload accommodationPayload) {
        return AccommodationDocument.builder()
                .accommodationId(accommodationPayload.getAccommodationId())
                .nights(accommodationPayload.getNights())
                .hotelName(accommodationPayload.getHotelName())
                .build();
    }
}
