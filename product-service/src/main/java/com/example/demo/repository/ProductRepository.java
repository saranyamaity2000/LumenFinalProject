package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	String tableName = "product_Service" ; 
	
	Page<Product> findAll(Pageable pageable);
	Page<Product> findAllByMerchantName(String merchantName, Pageable pageable	);
	Page<Product> findAllByInventoryGreaterThan(int inventory, Pageable pageable);
	@Query(value = "Select * from " + tableName + " where inventory=0", nativeQuery = true)
	Page<Product> getFinishedProduct(Pageable pageable);
}
