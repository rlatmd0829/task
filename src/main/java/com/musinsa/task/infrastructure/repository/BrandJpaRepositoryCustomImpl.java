package com.musinsa.task.infrastructure.repository;

import static com.musinsa.task.infrastructure.persistence.QBrandEntity.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musinsa.task.infrastructure.persistence.BrandEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BrandJpaRepositoryCustomImpl implements BrandJpaRepositoryCustom{
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<BrandEntity> getAllBrands() {
		return jpaQueryFactory.selectFrom(brandEntity)
			.leftJoin(brandEntity.products).fetchJoin()
			.fetch();
	}
}
