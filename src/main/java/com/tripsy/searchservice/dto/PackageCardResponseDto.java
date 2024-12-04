package com.tripsy.searchservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class PackageCardResponseDto {
    private Long packageId;
    private String title;
    private Double basePrice;
    private Integer durationInNights;
    private Long activityCount;
    private Integer accommodationCount;
    private Map<String, Integer> destinationNights;
    private String image;

//    public PackageCardResponseDto(final Long packageId, final String title, final Double basePrice,
//                                  final Integer durationInDays, final Long activityCount,
//                                  final Integer accommodationCount, final List<String> destinations,
//                                  final String image) {
//        this.packageId = packageId;
//        this.title = title;
//        this.basePrice = basePrice;
//        this.durationInDays = durationInDays;
//        this.activityCount = activityCount;
//        this.accommodationCount = accommodationCount;
//        this.destinationNames = destinations;
//        this.image = image;
//    }

}