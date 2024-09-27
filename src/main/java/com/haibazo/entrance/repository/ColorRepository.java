package com.haibazo.entrance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haibazo.entrance.entity.ColorEntity;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Integer> {

}
