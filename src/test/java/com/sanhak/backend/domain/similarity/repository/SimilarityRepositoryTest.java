package com.sanhak.backend.domain.similarity.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.sanhak.backend.domain.CA.dto.request.CAPageRequest;
import com.sanhak.backend.domain.CA.entity.CafeArticle;
import com.sanhak.backend.domain.CA.repository.CARepository;
import com.sanhak.backend.domain.RO.entity.RepairOrder;
import com.sanhak.backend.domain.RO.repository.RORepository;
import com.sanhak.backend.domain.similarity.entity.Similarity;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.LongStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
class SimilarityRepositoryTest {

    @Autowired
    private RORepository roRepository;
    @Autowired
    private CARepository caRepository;
    @Autowired
    private SimilarityRepository similarityRepository;

    @BeforeAll
    public void init() {
        LongStream.rangeClosed(1L, 22L)
                .forEach(idx -> {
                    CafeArticle ca = CafeArticle.builder()
                            .cafeName("cafeName" + idx)
                            .boardName("boardName" + idx)
                            .writer("writer" + idx)
                            .title("title" + idx)
                            .content("content" + idx)
                            .url("url" + idx)
                            .createdAt(LocalDateTime.now())
                            .keywords("keywords" + idx).build();
                    caRepository.save(ca);

                    for (int i = 0; i < 4; i++) {
                        RepairOrder ro = RepairOrder.builder()
                                .vehicleType("vt" + (idx+i))
                                .partNumber("partNumber" + (idx+i))
                                .causePart("causePart" + (idx+i))
                                .causePartNameKor("causePartNameKor" + (idx+i))
                                .causePartNameEng("causePartNameEng" + (idx+i))
                                .bigPhenom(pickBigPhenom(4*(idx-1)+i+1))
                                .subPhenom("subPhenom" + (idx+i))
                                .specialNote("specialNote" + (idx+i))
                                .location("location" + (idx+i))
                                .causePartCluster("causePartCluster" + (idx+i))
                                .problematic("problematic" + (idx+i))
                                .cause("cause" + (idx+i)).build();
                        roRepository.save(ro);
                        similarityRepository.save(new Similarity(ca, ro));
                    }
                });
    }

    private String pickBigPhenom(Long idx) {
        if (idx % 4 == 0) {
            return "진동";
        }
        if (idx % 4 == 1) {
            return "경고등 점등";
        }
        if (idx % 4 == 2) {
            return "기타";
        }
        return "냄새 과다";
    }

    @Test
    public void findAllByCafeArticleTest() throws Exception {
        //given
        Long caId = 1L;
        int idx=1;
        //when
        CafeArticle ca = caRepository.getReferenceById(caId);
        List<Similarity> result = similarityRepository.findAllByCafeArticle(ca);
        //then
        assertThat(result.size()).isEqualTo(4);
        for (Similarity similarity : result) {
            assertThat(similarity.getRepairOrder().getCause()).isEqualTo("cause" + idx);
            idx++;
        }
    }

    @Test
    public void findAllByRepairOrder_BigPhenomWithPagingTest() throws Exception {
        //given
        String bigPhenom = "경고등 점등";
        CAPageRequest req = new CAPageRequest();
        //when
        Page<Similarity> result = similarityRepository.findDistinctByRepairOrder_BigPhenom(bigPhenom,
                req.getPageRequest());
        //then
        int idx=1;
        assertThat(result.getTotalElements()).isEqualTo(22L);
        assertThat(result.getTotalPages()).isEqualTo(6);
        for (Similarity similarity : result.getContent()) {
            assertThat(similarity.getCafeArticle().getContent()).isEqualTo("content"+idx);
            assertThat(similarity.getId()).isEqualTo((idx-1)*4+1);
            idx++;
        }
    }

    @Test
    public void findAllByRepairOrder_BigPhenomWithoutPagingTest() throws Exception{
        //given
        String bigPhenom = "경고등 점등";
        //when
        List<Similarity> result = similarityRepository.findDistinctByRepairOrder_BigPhenom(
                bigPhenom);
        //then
        assertThat(result.size()).isEqualTo(22);
        for (Similarity similarity : result) {
            assertThat(similarity.getId() % 4).isEqualTo(1);
        }
    }


}