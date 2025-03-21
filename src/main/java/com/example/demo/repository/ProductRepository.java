package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProductEntity;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
