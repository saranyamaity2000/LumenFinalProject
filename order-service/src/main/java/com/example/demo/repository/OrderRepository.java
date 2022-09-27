package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Integer> {
	Page<Order> findAllByUsername(String username, Pageable pageable);
}
