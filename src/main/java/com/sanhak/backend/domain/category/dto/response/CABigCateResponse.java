package com.sanhak.backend.domain.category.dto.response;

import lombok.Getter;

@Getter
public class CABigCateResponse {

    private String label;
    private Long value;

    public CABigCateResponse(String label, Long value) {
        this.label = label;
        this.value = value;
    }
}
