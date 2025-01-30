package com.tripsy.searchservice.service;

import com.tripsy.searchservice.dto.DurationRangeDto;
import com.tripsy.searchservice.dto.PackageCardResponseDto;
import com.tripsy.searchservice.dto.PriceRangeDto;
import com.tripsy.searchservice.entity.PackageDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PackageSearchService {

    void createPackageDocument(PackageDocument packageDocument);

    Page<PackageCardResponseDto> searchPackages(Pageable pageable, String keyword);

    Page<PackageCardResponseDto> searchPackages(Pageable pageable, String destination,
                                                PriceRangeDto priceRange, DurationRangeDto durationRange);

    void upsertDocument(PackageDocument packageDocument);

    void deleteDocument(Long packageId);
}

