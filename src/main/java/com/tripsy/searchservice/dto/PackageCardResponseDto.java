package com.tripsy.searchservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PackageCardResponseDto {
    private Long packageId;
    private String title;
    private Double basePrice;
    private Integer durationInDays;
    private Long activityCount;
    private Integer accommodationCount;
    private List<String> destinationNames;


    public PackageCardResponseDto(final Long packageId, final String title, final Double basePrice,
                                  final Integer durationInDays, final Long activityCount,
                                  final Integer accommodationCount, final List<String> destinations) {
        this.packageId = packageId;
        this.title = title;
        this.basePrice = basePrice;
        this.durationInDays = durationInDays;
        this.activityCount = activityCount;
        this.accommodationCount = accommodationCount;
        this.destinationNames = destinations;
    }

}