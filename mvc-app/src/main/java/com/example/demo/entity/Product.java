
package com.example.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import lombok.Data;

@Data
public class Product {
	private int productId;
	private String productName; 
	private String merchantName; 
	private int invertory;
}
