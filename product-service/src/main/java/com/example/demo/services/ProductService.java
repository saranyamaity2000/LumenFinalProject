package com.example.demo.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	private ProductRepository repo;
	
	public ProductService(ProductRepository repo) {
		super();
		this.repo = repo;
	}
	
//	//get all products
//	public List<Product> findAll(){
//		return this.repo.findAll();
//	}
//	
	// get all products paged
	public Page<Product> findByPage(int page,int pageSize){
		 Pageable paging = PageRequest.of(page,pageSize);
		 Page<Product> pagedResult = repo.findAll(paging);
		 
		 return pagedResult;
	}
	
	// get All products by merchant
	public Page<Product> findByMerchantName(int page,int pageSize, String merchantName){
		Pageable pagining = PageRequest.of(page, pageSize);
		Page<Product> pagedResult = repo.findAllByMerchantName(merchantName, pagining);
		return pagedResult;
	}
	
//	// get product created by merchant
//	public List<Product> findByMerchantName(String merchantName){
//		return this.repo.findByMerchantName(merchantName);
//	}
	
	// add new product
	public Product save(Product product) {
		return this.repo.save(product);
	}
	
	// update product
	public Product update(Product product) {
		return this.repo.save(product);
	}
	
	// get available products
	public Page<Product> getAvailableProduct(int page, int pageSize){
		Pageable pagining = PageRequest.of(page, pageSize);
		Page<Product> pagedResult = this.repo.findAllByInventoryGreaterThan(0, pagining);
		return pagedResult;
	}
	
	// get finished products
	public Page<Product> getFinishedProduct(int page,int pageSize){
		Pageable pagining = PageRequest.of(page, pageSize);
		return this.repo.getFinishedProduct(pagining);
	}
}
