package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;

@Controller
public class ClientController {
	@Autowired
	private RestTemplate template;

	
	public ClientController() {
		super();
	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String WelcomePage() {
		return "index";
	}
	
	@GetMapping(path =  "/product-section")
	public String ProductSection() {
		return "product-section"; 
	}
	
	@GetMapping(path =  "/order-section")
	public String OrderSection() {
		return "order-section"; 
	}
	
	@GetMapping(path="/order-section/orders")
	public String getOrders(Model model) {
	    ResponseEntity<Order[]> orders  = template.getForEntity("lb://ORDER-SERVICE/list", Order[].class) ;
		model.addAttribute("orders", orders.getBody());
		return "orders" ; 
	}
	
	
	@PostMapping(path="/order-section/orders-by-user")
	public String postOrdersByUser(@RequestParam("username") String username, Model model) {
	    ResponseEntity<Order[]> orders  = template.getForEntity("lb://ORDER-SERVICE/list/" + username, Order[].class) ;
//		model.addAttribute("orders", orders.getBody());
//		return "orders"; 
	    return getOrdersByUserAndPage(username, 0, model); 
	}
	
	@GetMapping(path="/order-section/orders-by-user/{username}/{pageNo}")
	public String getOrdersByUserAndPage(@PathVariable("username") String username, @PathVariable("pageNo") int pageNo, Model model) {
	    ResponseEntity<Order[]> orders  = template.getForEntity("lb://ORDER-SERVICE/list/" + username + "?page=" + pageNo , Order[].class) ;
	    if(orders.getBody() == null) {
	    	return "error404"; 	
	    }
	    else {
	    	model.addAttribute("orders", orders.getBody());
	    	orders  = template.getForEntity("lb://ORDER-SERVICE/list/" + username + "?page=" + (pageNo + 1) , Order[].class) ;
	    	System.out.println(orders.getBody());
	    	model.addAttribute("pageNo", pageNo); 
	    	model.addAttribute("username", username) ; 
	    	model.addAttribute("hasNextPage", !(orders.getBody()==null)); 
			return "orders-by-user-page" ; 
	    }
	}
}
