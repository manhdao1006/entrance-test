package com.haibazo.entrance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haibazo.entrance.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findByProductCode(String productCode);

    boolean existsByProductCode(String productCode);

    void deleteByProductCode(String productCode);
}
