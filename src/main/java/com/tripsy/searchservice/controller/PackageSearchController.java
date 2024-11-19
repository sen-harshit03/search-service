package com.tripsy.searchservice.controller;

import com.tripsy.searchservice.entity.PackageDocument;
import com.tripsy.searchservice.service.PackageSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class PackageSearchController {

    private final PackageSearchService packageSearchService;
    @GetMapping
    public ResponseEntity<List<PackageDocument>> getAll() {
        return ResponseEntity.ok(packageSearchService.getAll());
    }
}
