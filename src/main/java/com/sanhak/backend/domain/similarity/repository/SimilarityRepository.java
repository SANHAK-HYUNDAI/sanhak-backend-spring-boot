package com.sanhak.backend.domain.similarity.repository;

import com.sanhak.backend.domain.CA.entity.CafeArticle;
import com.sanhak.backend.domain.similarity.entity.Similarity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimilarityRepository extends JpaRepository<Similarity, Long> {

    @EntityGraph(attributePaths = {"repairOrder"})
    public List<Similarity> findAllByCafeArticle(CafeArticle cafeArticle);

    @EntityGraph(attributePaths = {"cafeArticle"})
    public Page<Similarity> findDistinctByRepairOrder_BigPhenom(String bigPhenom, Pageable pageable);

    @EntityGraph(attributePaths = {"cafeArticle"})
    public List<Similarity> findDistinctByRepairOrder_BigPhenom(String bigPhenom);
}
