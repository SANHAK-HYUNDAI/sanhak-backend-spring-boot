package com.sanhak.backend.domain.category.service;

import com.sanhak.backend.domain.category.dto.CategoryResponseAssembler;
import com.sanhak.backend.domain.category.dto.response.CABigCateResponse;
import com.sanhak.backend.domain.category.dto.response.CASubCateResponse;
import com.sanhak.backend.domain.category.entity.CABigCategory;
import com.sanhak.backend.domain.category.entity.CASubCategory;
import com.sanhak.backend.domain.category.repository.CABigCategoryRepository;
import com.sanhak.backend.domain.category.repository.CASubCategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CACategoryService {

    private final CABigCategoryRepository caBigCategoryRepository;
    private final CASubCategoryRepository caSubCategoryRepository;
    private final CategoryResponseAssembler responseAssembler;

    public List<CASubCateResponse> getAllSubCategories() {
        List<CASubCategory> result = caSubCategoryRepository.findAll();
        long sum = sumSubCateCount(result);

        List<CASubCateResponse> responses = result.stream()
                .map(category -> responseAssembler.createCASubCateResponse(category, sum))
                .collect(Collectors.toUnmodifiableList());

        return responses;
    }

    private long sumSubCateCount(List<CASubCategory> result) {
        long sum=0L;
        for (CASubCategory caSubCategory : result) {
            sum+= caSubCategory.getCount();
        }

        return sum;
    }

    public List<CABigCateResponse> getAllBigCategories() {
        List<CABigCategory> result = caBigCategoryRepository.findAll();

        List<CABigCateResponse> responses = result.stream()
                .map(category -> responseAssembler.createCABigCateResponse(category))
                .collect(Collectors.toUnmodifiableList());

        return responses;
    }

    private long sumBigCateCount(List<CABigCategory> result) {
        long sum=0L;
        for (CABigCategory caBigCategory : result) {
            sum+= caBigCategory.getCount();
        }

        return sum;
    }

}
