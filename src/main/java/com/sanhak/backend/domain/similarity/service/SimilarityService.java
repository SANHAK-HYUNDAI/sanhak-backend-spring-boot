package com.sanhak.backend.domain.similarity.service;

import com.sanhak.backend.domain.CA.dto.request.CAPageRequest;
import com.sanhak.backend.domain.CA.entity.CafeArticle;
import com.sanhak.backend.domain.RO.dto.response.ROResponse;
import com.sanhak.backend.domain.similarity.entity.Similarity;
import com.sanhak.backend.domain.similarity.repository.SimilarityRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SimilarityService {

    private final SimilarityRepository similarityRepository;

    public Page<Similarity> getSimilaritiesByBigPhenom(CAPageRequest request) {
        Page<Similarity> result = similarityRepository.findDistinctByRepairOrder_BigPhenom(
                request.getBigPhenom(), request.getPageRequest());

        return result;
    }

    public List<Similarity> getAllSimilaritiesByBigPhenom(String bigPhenom){
        List<Similarity> result = similarityRepository.findDistinctByRepairOrder_BigPhenom(
                bigPhenom);

        return result;
    }

    public List<ROResponse> getRepairOrdersByCafeArticle(CafeArticle cafeArticle) {
        List<ROResponse> result = similarityRepository.findAllByCafeArticle(cafeArticle).stream()
                .map(similarity -> new ROResponse(similarity.getRepairOrder()))
                .collect(Collectors.toUnmodifiableList());

        return result;
    }
}
