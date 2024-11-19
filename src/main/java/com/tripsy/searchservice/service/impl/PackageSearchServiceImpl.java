package com.tripsy.searchservice.service.impl;

import com.tripsy.searchservice.entity.PackageDocument;
import com.tripsy.searchservice.repository.PackageSearchRepository;
import com.tripsy.searchservice.service.PackageSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class PackageSearchServiceImpl implements PackageSearchService {

    private final PackageSearchRepository packageSearchRepository;

    @Override
    public void createPackageDocument(final PackageDocument packageDocument) {
        log.info("Saving package document : {}", packageDocument);
        packageSearchRepository.save(packageDocument);
    }

    @Override
    public List<PackageDocument> getAll() {
        Iterable<PackageDocument> iterable = packageSearchRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
