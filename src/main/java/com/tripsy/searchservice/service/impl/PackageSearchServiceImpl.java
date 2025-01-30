package com.tripsy.searchservice.service.impl;

import com.tripsy.searchservice.dto.DurationRangeDto;
import com.tripsy.searchservice.dto.PackageCardResponseDto;
import com.tripsy.searchservice.dto.PriceRangeDto;
import com.tripsy.searchservice.entity.PackageDocument;
import com.tripsy.searchservice.mapper.PackageDocumentMapper;
import com.tripsy.searchservice.repository.PackageSearchRepository;
import com.tripsy.searchservice.service.PackageSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Override
    public Page<PackageCardResponseDto> searchPackages(final Pageable pageable, String destination,
                                                       final PriceRangeDto priceRange,
                                                       final DurationRangeDto durationRange) {
        log.info("Keyword : {}", destination);
        log.info("Price Range : {}", priceRange);
        log.info("Duration Range : {}", durationRange);
        if (Objects.isNull(destination) || destination.trim().isEmpty()) {
            return getPackageCardsFromRepository(packageSearchRepository.findAll(pageable), pageable);
        }

        return getPackageCardsFromRepository(packageSearchRepository.searchWithFilters(
                destination,
                priceRange.minPrice(), priceRange.maxPrice(),
                durationRange.minDuration(), durationRange.maxDuration(),
                pageable
        ), pageable);

    }

    @Override
    public void upsertDocument(final PackageDocument document) {
        log.info("Upserting document: {}", document);
        packageSearchRepository.findById(document.getPackageId())
                .ifPresentOrElse(existingPackage -> {
                            mergeImages(existingPackage, document);
                            packageSearchRepository.save(document);
                        },
                        () -> packageSearchRepository.save(document));

    }

    @Override
    public void deleteDocument(final Long packageId) {
        packageSearchRepository.deleteById(packageId);
    }

    private void mergeImages(final PackageDocument existingPackage, final PackageDocument updatedPackage) {
        final Set<String> mergedUrls = Stream.concat(
                existingPackage.getImageUrls().stream(),
                updatedPackage.getImageUrls().stream()
        ).collect(Collectors.toSet());

        updatedPackage.setImageUrls(new ArrayList<>(mergedUrls));
    }


    private Page<PackageCardResponseDto> getPackageCardsFromRepository(Page<PackageDocument> packageDocuments, Pageable pageable) {
        log.info(String.valueOf(packageDocuments.getTotalElements()));
        List<PackageCardResponseDto> packageCardResponse = packageDocuments.getContent().stream()
                .map(this::convertPackageToCardResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(packageCardResponse, pageable, packageDocuments.getTotalElements());
    }

    public PackageCardResponseDto convertPackageToCardResponse(final PackageDocument packageDocument) {
        return packageDocumentMapper.toCardResponse(packageDocument);
    }

}
