
package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_service")
public class Product {
	 @Id
	 @Column(name="product_id")
	 private int productId;
	 
	 @Column(name="product_name")
	 private String productName;
	 
	 @Column(name="merchant_name")
	 private String merchantName ; 
	 
	 @Column(name="inventory")
	 private String inventory ; 
	 
}
