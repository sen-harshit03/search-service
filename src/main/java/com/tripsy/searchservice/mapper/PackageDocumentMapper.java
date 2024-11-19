package com.tripsy.searchservice.mapper;

import com.tripsy.common.PackagePayload;
import com.tripsy.common.PackageSearchPayload;
import com.tripsy.searchservice.entity.PackageDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PackageDocumentMapper {

    private final AccommodationDocumentMapper accommodationDocumentMapper;
    private final DestinationDocumentMapper destinationDocumentMapper;
    private final ItineraryDocumentMapper itineraryDocumentMapper;

    public PackageDocument toDocument(final PackageSearchPayload payload) {
        final PackagePayload packagePayload = payload.getPackagePayload();

        final PackageDocument packageDocument =  PackageDocument.builder()
                .packageId(packagePayload.getPackageId())
                .title(packagePayload.getTitle())
                .description(packagePayload.getDescription())
                .activityCount(packagePayload.getActivityCount())
                .durationInDays(packagePayload.getDurationInDays())
                .basePrice(packagePayload.getBasePrice())
                .accommodationCount(packagePayload.getAccommodationCount())
                .accommodations(packagePayload.getAccommodations().stream()
                        .map(accommodationDocumentMapper::toDocument).collect(Collectors.toList()))
                .destinations(packagePayload.getDestinations().stream()
                        .map(destinationDocumentMapper::toDocument).collect(Collectors.toList()))
                .itineraries(packagePayload.getItineraries().stream()
                        .map(itineraryDocumentMapper::toDocument).collect(Collectors.toList()))
                .imageUrls(payload.getImageUrls())
                .build();

        return packageDocument;

    }
}
