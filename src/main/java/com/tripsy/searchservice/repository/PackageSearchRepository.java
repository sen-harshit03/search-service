package com.tripsy.searchservice.repository;

import com.tripsy.searchservice.entity.PackageDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PackageSearchRepository extends ElasticsearchRepository<PackageDocument, Long> {


}
