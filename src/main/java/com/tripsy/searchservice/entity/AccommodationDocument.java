package com.tripsy.searchservice.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDocument {
    private Long accommodationId;
    private String hotelName;
    private Integer nights;
}
