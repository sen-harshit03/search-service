package com.tripsy.searchservice.service.impl;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import com.tripsy.searchservice.dto.PackageCardResponseDto;
import com.tripsy.searchservice.entity.PackageDocument;
import com.tripsy.searchservice.mapper.PackageDocumentMapper;
import com.tripsy.searchservice.repository.PackageSearchRepository;
import com.tripsy.searchservice.service.PackageSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.Queries;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class PackageSearchServiceImpl implements PackageSearchService {

    private final PackageSearchRepository packageSearchRepository;
    private final PackageDocumentMapper packageDocumentMapper;

    @Override
    public void createPackageDocument(final PackageDocument packageDocument) {
        log.info("Saving package document : {}", packageDocument);
        packageSearchRepository.save(packageDocument);
    }


    @Override
    public Page<PackageCardResponseDto> searchPackages(final Pageable pageable, final String keyword) {
        if (Objects.isNull(keyword) || keyword.trim().isEmpty()) {
           return getPackageCardsFromRepository(packageSearchRepository.findAll(pageable), pageable);
        }

        return getPackageCardsFromRepository(packageSearchRepository.searchByDestinationOrCountry(keyword, pageable), pageable);

    }

    private Page<PackageCardResponseDto> getPackageCardsFromRepository(Page<PackageDocument> packageDocuments, Pageable pageable) {
        List<PackageCardResponseDto> packageCardResponse = packageDocuments.getContent().stream()
                .map(this::convertPackageToCardResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(packageCardResponse, pageable, packageDocuments.getTotalElements());
    }

    public PackageCardResponseDto convertPackageToCardResponse(final PackageDocument packageDocument) {
        return packageDocumentMapper.toCardResponse(packageDocument);
    }

}
