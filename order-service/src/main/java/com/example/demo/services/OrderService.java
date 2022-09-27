package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {
	
	private OrderRepository repo;
	
	@Autowired
	public OrderService(OrderRepository repo) {
		super();
		this.repo = repo;
	}
	
	// get all orders
	public List<Order> findAll(){
		return this.repo.findAll();
	}
	
	// get All products by username
		public Page<Order> findByUsername(int page,int pageSize, String username){
			Pageable pagining = PageRequest.of(page, pageSize);
			Page<Order> pagedResult = repo.findAllByUsername(username, pagining);
			return pagedResult;
		}
	
	// find by orderId
	public Order findById(int id) {
		String message = new StringBuilder("Element with given Id ")
				.append(id)
				.append(" not found")
				.toString();
		return this.repo.findById(id).
				orElseThrow(() -> new RuntimeException( message));
	}
	
	// add order
	public Order save(Order order) {
		return this.repo.save(order);
	}
	
	// update order
	public Order update(Order order) {
		return this.repo.save(order);
	}
	
}
