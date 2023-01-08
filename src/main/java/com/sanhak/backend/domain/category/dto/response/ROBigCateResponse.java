package com.sanhak.backend.domain.category.dto.response;

import lombok.Getter;

@Getter
public class ROBigCateResponse {

    private String label;
    private Long value;

    public ROBigCateResponse(String name, Long value) {
        this.label = name;
        this.value = value;
    }
}
