package com.musinsa.task.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musinsa.task.infrastructure.persistence.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long>, ProductJpaRepositoryCustom {
}
