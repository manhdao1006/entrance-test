package com.haibazo.entrance.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.haibazo.entrance.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    Optional<ProductEntity> findByProductCode(String productCode);

    boolean existsByProductCode(String productCode);

    void deleteByProductCode(String productCode);

    List<ProductEntity> findAllByDelFlag(String delFlag);

    List<ProductEntity> findAllByDelFlagOrderByPriceAsc(String delFlag);

    List<ProductEntity> findAllByDelFlagOrderByPriceDesc(String delFlag);

    @Query("SELECT p FROM ProductEntity p JOIN p.colors c "
            + "WHERE ((LOWER(c.colorName) = LOWER(:colorName)) "
            + "OR (LOWER(p.style.category.categoryName) = LOWER(:categoryName)) "
            + "OR (LOWER(p.style.styleName) = LOWER(:styleName))) "
            + "AND p.delFlag = '1'")
    List<ProductEntity> findAllByKeyword(@Param("colorName") String colorName,
            @Param("categoryName") String categoryName, @Param("styleName") String styleName);

    @Query("SELECT p FROM ProductEntity p WHERE (p.price BETWEEN :fromPrice AND :toPrice) AND p.delFlag = '1'")
    List<ProductEntity> findAllByPriceBetween(@Param("fromPrice") BigDecimal fromPrice,
            @Param("toPrice") BigDecimal toPrice);
}
