package com.tripsy.searchservice.controller;

import com.tripsy.searchservice.dto.DurationRangeDto;
import com.tripsy.searchservice.dto.PackageCardResponseDto;
import com.tripsy.searchservice.dto.PriceRangeDto;
import com.tripsy.searchservice.service.PackageSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class PackageSearchController {

    private final PackageSearchService packageSearchService;

    @GetMapping
    public ResponseEntity<Page<PackageCardResponseDto>> searchPackages(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "10") final Integer size,
            @RequestParam(defaultValue = "0") final Double minPrice,
            @RequestParam(defaultValue = "100000") final Double maxPrice,
            @RequestParam(defaultValue = "1") final Integer minDuration,
            @RequestParam(defaultValue = "10") final Integer maxDuration,
            @RequestParam(required = false) final String destination
    ) {
        final Pageable pageable = PageRequest.of(page, size);
//        return ResponseEntity.ok(packageSearchService.searchPackages(pageable, keyword));
        return ResponseEntity.ok(packageSearchService.searchPackages(pageable, destination,
                new PriceRangeDto(minPrice, maxPrice),
                new DurationRangeDto(minDuration, maxDuration)));
    }


    @GetMapping("/all")
    public ResponseEntity<Page<PackageCardResponseDto>> getAll(
            @RequestParam(defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "10") final Integer size,
            @RequestParam(required = false) final String keyword
    ) {
        final Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(packageSearchService.searchPackages(pageable, keyword));
    }


}
