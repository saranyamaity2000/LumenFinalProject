package com.example.demo.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Product;
import com.example.demo.services.ProductService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ProductController {
	private ProductService service;
	
//	@GetMapping(path = "/list")
//	public List<Product> findAll(){
//		return this.service.findAll();
//	}
	
	@GetMapping(path = "/list")
	public ResponseEntity<List<Product>> findByPage(@RequestParam  (defaultValue = "0") int page,
		      @RequestParam(defaultValue = "3") int pageSize)
		      {
//		return this.service.findByPage(page, pageSize);
		try {
			List<Product> products = new ArrayList<Product>();
//		    Pageable pagingSort = PageRequest.of(page, pageSize);
		    
		    Page<Product> pageProds = this.service.findByPage(page,pageSize);
		    
		    products = pageProds.getContent();
		    
		    if(products.isEmpty()) {
		    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(products, HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping(path = "/list/{mechant}")
	public ResponseEntity<List<Product>> findByMerchantName(@RequestParam  (defaultValue = "0") int page,
		      @RequestParam(defaultValue = "3") int pageSize,
		      @PathVariable("mechant") String merchantName)
		      {
		try {
			List<Product> products = new ArrayList<Product>();
//		    Pageable pagingSort = PageRequest.of(page, pageSize);
		    
		    Page<Product> pageProds = this.service.findByMerchantName(page, pageSize, merchantName);
		    
		    products = pageProds.getContent();
		    
		    if(products.isEmpty()) {
		    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    
		    return new ResponseEntity<>(products , HttpStatus.OK);
		      
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
    }
	
	@PostMapping(path = "/save")
	public ResponseEntity<Product> add(@RequestBody Product product){
		Product addedProduct = this.service.save(product);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("list/{id}")
				.buildAndExpand(product.getProductId())
				.toUri();
				
		
		return ResponseEntity.created(location).body(addedProduct);
	}
	
	
	@PutMapping(path = "/save")
	public ResponseEntity<Product> update(@RequestBody Product product){
		Product updated = this.service.update(product);
		
		return ResponseEntity.ok().body(updated);
		
	}
	
	@GetMapping(path = "/list/available")
	public ResponseEntity<List<Product>> findAvailable(@RequestParam  (defaultValue = "0") int page,
		      @RequestParam(defaultValue = "3") int pageSize)
		      {
		try {
			List<Product> products = new ArrayList<Product>();
		    Pageable pagingSort = PageRequest.of(page, pageSize);
		    
		    Page<Product> pageProds = this.service.getAvailableProduct(page,pageSize);
		    
		    products = pageProds.getContent();
		    
		    if(products.isEmpty()) {
		    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		   
		    return new ResponseEntity<>(products , HttpStatus.OK);
		      
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/list/not-available")
	public ResponseEntity<List<Product>> findNonAvailable(@RequestParam  (defaultValue = "0") int page,
		      @RequestParam(defaultValue = "3") int pageSize)
		      {
		try {
			List<Product> products = new ArrayList<Product>();
		    Pageable pagingSort = PageRequest.of(page, pageSize);
		    
		    Page<Product> pageProds = this.service.getFinishedProduct(page,pageSize);
		    
		    products = pageProds.getContent();
		    
		    if(products.isEmpty()) {
		    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    
		    return new ResponseEntity<>(products , HttpStatus.OK);
		    
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
