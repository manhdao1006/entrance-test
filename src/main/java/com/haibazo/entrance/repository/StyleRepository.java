package com.haibazo.entrance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haibazo.entrance.entity.StyleEntity;

@Repository
public interface StyleRepository extends JpaRepository<StyleEntity, Integer> {

}
