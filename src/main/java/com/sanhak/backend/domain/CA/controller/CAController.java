package com.sanhak.backend.domain.CA.controller;

import com.sanhak.backend.domain.CA.dto.request.CAPageRequest;
import com.sanhak.backend.domain.CA.dto.response.CAPageResponse;
import com.sanhak.backend.domain.CA.dto.response.CADetailResponse;
import com.sanhak.backend.domain.CA.dto.response.CASimpleResponse;
import com.sanhak.backend.domain.CA.dto.response.CAStatisticsResponse;
import com.sanhak.backend.domain.CA.service.CAService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/CAs")
@RequiredArgsConstructor
public class CAController {

    private final CAService caService;

    @GetMapping
    public ResponseEntity<CAPageResponse> getCAsByBigPhenom(CAPageRequest caPageRequest) {
        CAPageResponse response = caService.getSimpleCAsByBigPhenom(caPageRequest);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CASimpleResponse>> getAllCAsByBigPhenom(String bigPhenom) {
        List<CASimpleResponse> response = caService.getAllCAsByBigPhenom(bigPhenom);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{ca_id}")
    public ResponseEntity<CADetailResponse> getCA(@PathVariable(name = "ca_id") Long caId) {
        CADetailResponse response = caService.getDetailCAById(caId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics")
    public ResponseEntity<CAStatisticsResponse> getCAStatistics() {
        CAStatisticsResponse response = caService.getCAStatistics();

        return ResponseEntity.ok(response);
    }
}
