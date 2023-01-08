package com.sanhak.backend.domain.RO.controller;

import com.sanhak.backend.domain.CA.dto.response.CAStatisticsResponse;
import com.sanhak.backend.domain.RO.dto.request.ROPageRequest;
import com.sanhak.backend.domain.RO.dto.response.ROPageResponse;
import com.sanhak.backend.domain.RO.dto.response.ROResponse;
import com.sanhak.backend.domain.RO.dto.response.ROStatisticsResponse;
import com.sanhak.backend.domain.RO.service.ROService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ROs")
@RequiredArgsConstructor
public class ROController {

    private final ROService roService;

    @GetMapping
    public ResponseEntity<ROPageResponse> getROs(ROPageRequest roPageRequest) {
        ROPageResponse response = roService.getROs(roPageRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ROResponse>> getROsWithoutPaging() {
        List<ROResponse> result = roService.getROsWithoutPaging();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/statistics")
    public ResponseEntity<ROStatisticsResponse> getROStatistics() {
        ROStatisticsResponse response = roService.getROStatistics();

        return ResponseEntity.ok(response);
    }
}
