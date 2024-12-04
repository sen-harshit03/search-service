package com.tripsy.searchservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.Map;

@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "packages")
public class PackageDocument {

    @Id
    private Long packageId;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Double)
    private Double basePrice;

    @Field(type = FieldType.Integer)
    private Integer durationInNights;

    @Field(type = FieldType.Long)
    private Long activityCount;

    @Field(type = FieldType.Integer)
    private Integer accommodationCount;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<ItineraryDocument> itineraries;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<AccommodationDocument> accommodations;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<DestinationDocument> destinations;

    @Field(type = FieldType.Object)
    private Map<String, Integer> destinationNights;

    @Field(type = FieldType.Keyword)
    private List<String> imageUrls;
}
