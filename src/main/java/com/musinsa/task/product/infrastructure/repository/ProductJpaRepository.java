package com.musinsa.task.product.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musinsa.task.product.infrastructure.persistence.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long>, ProductJpaRepositoryCustom {
}
