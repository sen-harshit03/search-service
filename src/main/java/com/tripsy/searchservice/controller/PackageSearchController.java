package com.tripsy.searchservice.controller;

import com.tripsy.searchservice.dto.PackageCardResponseDto;
import com.tripsy.searchservice.entity.PackageDocument;
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

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class PackageSearchController {

    private final PackageSearchService packageSearchService;
    @GetMapping
    public ResponseEntity<Page<PackageCardResponseDto>> searchPackages(
            @RequestParam(defaultValue = "0") final int page,
            @RequestParam(defaultValue = "10") final int size,
            @RequestParam(required = false) final String keyword
    ) {
        final Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(packageSearchService.searchPackages(pageable, keyword));
    }


}
