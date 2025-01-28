package com.tripsy.searchservice.repository;

import com.tripsy.searchservice.entity.PackageDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PackageSearchRepository extends ElasticsearchRepository<PackageDocument, Long> {


//    @Query("{\"bool\": {\"should\": ["
//            + "{\"nested\": {"
//            + "  \"path\": \"destinations\","
//            + "  \"query\": {\"match\": {\"destinations.destinationName\": \"?0\"}}"
//            + "} },"
//            + "{\"nested\": {"
//            + "  \"path\": \"destinations\","
//            + "  \"query\": {\"match\": {\"destinations.country\": \"?0\"}}"
//            + "} }"
//            + "]}}")
//    Page<PackageDocument> searchByDestinationOrCountry(String searchText, Pageable pageable);

    @Query("{\"bool\": {\"should\": ["
            + "{\"nested\": {"
            + "  \"path\": \"destinations\","
            + "  \"query\": {\"match\": {\"destinations.destinationName\": \"?0\"}}"
            + "} },"
            + "{\"nested\": {"
            + "  \"path\": \"destinations\","
            + "  \"query\": {\"match\": {\"destinations.country\": \"?0\"}}"
            + "} }"
            + "]}}")
    Page<PackageDocument> searchByDestinationOrCountry(String keyword, Pageable pageable);

    Page<PackageDocument> findAll(Pageable pageable);

@Query("""
    {
        "bool": {
            "must": [
                {
                    "multi_match": {
                        "query": "?0",
                        "fields": ["destinations.destinationName", "destinations.country"]
                    }
                }
            ],
            "filter": [
                {
                    "range": {
                        "basePrice": {
                            "gte": ?1,
                            "lte": ?2
                        }
                    }
                },
                {
                    "range": {
                        "durationInNights": {
                            "gte": ?3,
                            "lte": ?4
                        }
                    }
                }
            ]
        }
    }
    """)

    Page<PackageDocument> searchWithFilters(
            String keyword,
            Double minPrice,
            Double maxPrice,
            Integer minDuration,
            Integer maxDuration,
            Pageable pageable
    );


}
