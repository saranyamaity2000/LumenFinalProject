
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
@Table(name = "order_service")
public class Order {
	 @Id
	 @Column(name="order_id")
	 private int orderId;
	 
	 @Column(name="product_id")
	 private int productId;
	 
	 @Column(name="username")
	 private String username;
}
