package com.sanhak.backend.domain.keyword.dto.response;

import lombok.Getter;

@Getter
public class KeywordResponse {

    private String text;
    private Integer value;

    public KeywordResponse(String text, Integer value) {
        this.text = text;
        this.value = value;
    }
}
