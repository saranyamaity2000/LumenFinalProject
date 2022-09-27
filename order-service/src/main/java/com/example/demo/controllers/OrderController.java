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

import com.example.demo.entity.Order;
import com.example.demo.services.OrderService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class OrderController {
	private OrderService service;
	
	@GetMapping(path = "/list")
	public List<Order> findAll(){
		return this.service.findAll();
	}
	
	@GetMapping(path = "/list/{user}")
	public ResponseEntity<List<Order>> findByMerchantName(@RequestParam  (defaultValue = "0") int page,
		      @RequestParam(defaultValue = "3") int pageSize,
		      @PathVariable("user") String user)
		      {
		try {
			List<Order> orders = new ArrayList<Order>();
//		    Pageable pagingSort = PageRequest.of(page, pageSize);
		    
		    Page<Order> pageOrders = this.service.findByUsername(page, pageSize, user);
		    
		    orders = pageOrders.getContent();
		    
		    if(orders.isEmpty()) {
		    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    
		    return new ResponseEntity<>(orders , HttpStatus.OK);
		      
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
    }
	
	@GetMapping(path = "/{orderId}")
	public Order findById(@PathVariable("orderId") int orderId) {
		return this.service.findById(orderId);
	}
	
	@PostMapping(path = "/save")
	public ResponseEntity<Order> add(@RequestBody Order order){
		Order addedProduct = this.service.save(order);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("{/id}")
				.buildAndExpand(order.getProductId())
				.toUri();
				
		
		return ResponseEntity.created(location).body(addedProduct);
	}
	
	
	@PutMapping(path = "/save")
	public ResponseEntity<Order> update(@RequestBody Order order){
		Order updated = this.service.update(order);
		
		return ResponseEntity.ok().body(updated);
		
	}
}
