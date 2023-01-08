package com.sanhak.backend.domain.category.dto;

import com.sanhak.backend.domain.category.dto.response.CABigCateResponse;
import com.sanhak.backend.domain.category.dto.response.CASubCateResponse;
import com.sanhak.backend.domain.category.dto.response.ROBigCateResponse;
import com.sanhak.backend.domain.category.dto.response.ROSubCateResponse;
import com.sanhak.backend.domain.category.entity.CABigCategory;
import com.sanhak.backend.domain.category.entity.CASubCategory;
import com.sanhak.backend.domain.category.entity.ROBigCategory;
import com.sanhak.backend.domain.category.entity.ROSubCategory;
import org.springframework.stereotype.Component;

@Component
public class CategoryResponseAssembler {

    public CASubCateResponse createCASubCateResponse(CASubCategory category, Long sum) {
        double rate = (double) category.getCount() / sum;
        return new CASubCateResponse(category.getName(), category.getBigCategory().getName(), rate);
    }

    public CABigCateResponse createCABigCateResponse(CABigCategory category) {
        return new CABigCateResponse(category.getName(), category.getCount());
    }

    public ROSubCateResponse createROSubCateResponse(ROSubCategory category, Long sum) {
        double rate = (double) category.getCount() / sum;
        return new ROSubCateResponse(category.getName(), category.getBigCategory().getName(), rate);
    }

    public ROBigCateResponse createROBigCateResponse(ROBigCategory category) {
        return new ROBigCateResponse(category.getName(), category.getCount());
    }
}
