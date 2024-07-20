package com.musinsa.task.product.infrastructure.repository;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.JPQLQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductJpaRepositoryCustomImpl implements ProductJpaRepositoryCustom{
	private final JPQLQueryFactory queryFactory;


}
