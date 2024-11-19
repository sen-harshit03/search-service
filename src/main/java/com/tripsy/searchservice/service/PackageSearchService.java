package com.tripsy.searchservice.service;

import com.tripsy.searchservice.entity.PackageDocument;

import java.util.List;

public interface PackageSearchService {

    void createPackageDocument(PackageDocument packageDocument);

    List<PackageDocument> getAll();
}

