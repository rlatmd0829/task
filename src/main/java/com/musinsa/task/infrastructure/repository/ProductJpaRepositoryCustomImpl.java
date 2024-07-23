package com.musinsa.task.infrastructure.repository;

import static com.musinsa.task.infrastructure.persistence.QBrandEntity.*;
import static com.musinsa.task.infrastructure.persistence.QProductEntity.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.musinsa.task.domain.model.Category;
import com.musinsa.task.infrastructure.persistence.ProductEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductJpaRepositoryCustomImpl implements ProductJpaRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<ProductEntity> getAllProductsByCategoryIn(List<Category> categories) {
		return jpaQueryFactory.selectFrom(productEntity)
			.leftJoin(productEntity.brand, brandEntity).fetchJoin()
			.where(productEntity.category.in(categories))
			.fetch();
	}

	@Override
	public List<ProductEntity> getAllProducts() {
		return jpaQueryFactory.selectFrom(productEntity)
			.leftJoin(productEntity.brand, brandEntity).fetchJoin()
			.fetch();
	}

	@Override
	public List<ProductEntity> getAllProductsByCategory(Category category) {
		return jpaQueryFactory.selectFrom(productEntity)
			.leftJoin(productEntity.brand, brandEntity).fetchJoin()
			.where(productEntity.category.eq(category))
			.fetch();
	}
}
