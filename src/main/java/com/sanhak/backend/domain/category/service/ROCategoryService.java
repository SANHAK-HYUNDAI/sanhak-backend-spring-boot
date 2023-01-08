package com.sanhak.backend.domain.category.service;

import com.sanhak.backend.domain.category.dto.CategoryResponseAssembler;
import com.sanhak.backend.domain.category.dto.response.ROBigCateResponse;
import com.sanhak.backend.domain.category.dto.response.ROSubCateResponse;
import com.sanhak.backend.domain.category.entity.ROBigCategory;
import com.sanhak.backend.domain.category.entity.ROSubCategory;
import com.sanhak.backend.domain.category.repository.ROBigCategoryRepository;
import com.sanhak.backend.domain.category.repository.ROSubCategoryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ROCategoryService {

    private final ROBigCategoryRepository roBigCategoryRepository;
    private final ROSubCategoryRepository roSubCategoryRepository;
    private final CategoryResponseAssembler responseAssembler;

    public List<ROSubCateResponse> getAllSubCategories() {
        List<ROSubCategory> result = roSubCategoryRepository.findAll();
        long sum = sumSubCateCount(result);

        List<ROSubCateResponse> responses = result.stream()
                .map(category -> responseAssembler.createROSubCateResponse(category, sum))
                .collect(Collectors.toUnmodifiableList());

        return responses;
    }

    private long sumSubCateCount(List<ROSubCategory> result) {
        long sum=0L;
        for (ROSubCategory roSubCategory : result) {
            sum+= roSubCategory.getCount();
        }

        return sum;
    }

    public List<ROBigCateResponse> getAllBigCategories() {
        List<ROBigCategory> result = roBigCategoryRepository.findAll();

        List<ROBigCateResponse> responses = result.stream()
                .map(category -> responseAssembler.createROBigCateResponse(category))
                .collect(Collectors.toUnmodifiableList());

        return responses;
    }

    private long sumBigCateCount(List<ROBigCategory> result) {
        long sum=0L;
        for (ROBigCategory roBigCategory : result) {
            sum+= roBigCategory.getCount();
        }

        return sum;
    }

}
