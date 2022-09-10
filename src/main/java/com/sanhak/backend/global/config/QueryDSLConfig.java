package com.sanhak.backend.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class QueryDSLConfig {
    private final EntityManager em;

    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}