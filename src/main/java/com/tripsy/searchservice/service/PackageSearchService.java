package com.tripsy.searchservice.service;

import com.tripsy.searchservice.dto.PackageCardResponseDto;
import com.tripsy.searchservice.entity.PackageDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PackageSearchService {

    void createPackageDocument(PackageDocument packageDocument);

    Page<PackageCardResponseDto> searchPackages(Pageable pageable, String keyword);

//    Page<PackageCardResponseDto> searchByDestinationOrCountry(String searchText, Pageable pageable)
}

