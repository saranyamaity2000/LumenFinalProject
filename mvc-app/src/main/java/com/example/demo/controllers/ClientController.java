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
import com.example.demo.utils.Utility;

@Controller
public class ClientController {
	@Autowired
	private RestTemplate template;
	
	public ClientController() {
		super();
	}

	//GENERAL CONTROLLER
	 
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String WelcomePage() {
		return "index";
	}
	
	// PRODUCT CONTROLLERS
	
	@GetMapping(path =  "/product-section")
	public String productSection() {
		return "product-section"; 
	}
	
	@GetMapping(path="/product-section/products")
	public String getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize, Model model) {
		ResponseEntity<Product[]> products  = template.getForEntity("lb://PRODUCT-SERVICE/list" + Utility.pageQuery(page, pageSize)  , Product[].class) ;
	    if(products.getBody() == null) {
	    	model.addAttribute("item" , "product") ; 
	    	return "shortage"; 		
	    }
	    else {
	    	model.addAttribute("products", products.getBody());
	    	products  = template.getForEntity("lb://PRODUCT-SERVICE/list" + Utility.pageQuery(page + 1, pageSize) , Product[].class) ;
	    	model.addAttribute("pageNo", page); 
	    	model.addAttribute("hasNextPage", !(products.getBody()==null)); 
	    	model.addAttribute("nextPagePath", "/product-section/products" +  Utility.pageQuery(page + 1, pageSize) ); 
	    	model.addAttribute("hasPreviousPage" , page >= 1); 
	    	model.addAttribute("previousPagePath", "/product-section/products" +  Utility.pageQuery(page - 1, pageSize));
	    	model.addAttribute("pageSize", pageSize) ;
	    	return "products"; 
	    }
	}
	
	@GetMapping(path="/product-section/products/{merchant}")
	public String getProductsByMerchantName(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize, @PathVariable String merchant, Model model) {
		ResponseEntity<Product[]> products  = template.getForEntity("lb://PRODUCT-SERVICE/list/" + merchant + Utility.pageQuery(page, pageSize)  , Product[].class) ;
	    if(products.getBody() == null) {
	    	model.addAttribute("item" , "product") ; 
	    	return "shortage"; 		
	    }
	    else {
	    	model.addAttribute("products", products.getBody());
	    	products  = template.getForEntity("lb://PRODUCT-SERVICE/list/" + merchant + Utility.pageQuery(page + 1, pageSize) , Product[].class) ;
	    	System.out.println(products.getBody());
	    	model.addAttribute("pageNo", page); 
	    	model.addAttribute("marchant", merchant) ; 
	    	model.addAttribute("hasNextPage", !(products.getBody()==null)); 
	    	model.addAttribute("nextPagePath", "/product-section/products/" + merchant +  Utility.pageQuery(page + 1, pageSize) ); 
	    	model.addAttribute("hasPreviousPage" , page >= 1); 
	    	model.addAttribute("previousPagePath", "/product-section/products/" + merchant +  Utility.pageQuery(page - 1, pageSize));
	    	model.addAttribute("pageSize", pageSize) ; 
			return "products" ; 
	    }
	}
	
	@GetMapping(path="/product-section/products/available")
	public String getAvailableProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize, Model model) {
		ResponseEntity<Product[]> products  = template.getForEntity("lb://PRODUCT-SERVICE/list/available" + Utility.pageQuery(page, pageSize)  , Product[].class) ;
	    if(products.getBody() == null) {
	    	model.addAttribute("item" , "product") ; 
	    	return "shortage"; 		
	    }
	    else {
	    	model.addAttribute("products", products.getBody());
	    	products  = template.getForEntity("lb://PRODUCT-SERVICE/list/available" + Utility.pageQuery(page + 1, pageSize) , Product[].class) ;
	    	System.out.println(products.getBody());
	    	model.addAttribute("pageNo", page);
	    	model.addAttribute("hasNextPage", !(products.getBody()==null)); 
	    	model.addAttribute("nextPagePath", "/product-section/products/available" +  Utility.pageQuery(page + 1, pageSize) ); 
	    	model.addAttribute("hasPreviousPage" , page >= 1); 
	    	model.addAttribute("previousPagePath", "/product-section/products/available" +  Utility.pageQuery(page - 1, pageSize));
	    	model.addAttribute("pageSize", pageSize) ; 
			return "products" ; 
	    }
	}
	
	@GetMapping(path="/product-section/products/not-available")
	public String getNotAvailableProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize, Model model) {
		ResponseEntity<Product[]> products  = template.getForEntity("lb://PRODUCT-SERVICE/list/not-available" + Utility.pageQuery(page, pageSize)  , Product[].class) ;
	    if(products.getBody() == null) {
	    	model.addAttribute("item" , "product") ; 
	    	return "shortage"; 		
	    }
	    else {
	    	model.addAttribute("products", products.getBody());
	    	products  = template.getForEntity("lb://PRODUCT-SERVICE/list/not-available" + Utility.pageQuery(page + 1, pageSize) , Product[].class) ;
	    	System.out.println(products.getBody());
	    	model.addAttribute("pageNo", page);
	    	model.addAttribute("hasNextPage", !(products.getBody()==null)); 
	    	model.addAttribute("nextPagePath", "/product-section/products/not-available" +  Utility.pageQuery(page + 1, pageSize) ); 
	    	model.addAttribute("hasPreviousPage" , page >= 1); 
	    	model.addAttribute("previousPagePath", "/product-section/products/not-available" +  Utility.pageQuery(page - 1, pageSize));
	    	model.addAttribute("pageSize", pageSize) ; 
			return "products" ; 
	    }
	}
	
	@PostMapping(path="/product-section/products-by-merchant")
	public String postProductsByMerchant(@RequestParam("username") String merchant, @RequestParam("pageSize") int pageSize, Model model) { 
	    return getProductsByMerchantName(0, pageSize, merchant, model); 
	}
	
	@GetMapping(path="/product-section/save")
	public String AddProduct(Model model) {
		model.addAttribute("command", new Product());
        return "add-product";
	}
	
	@PostMapping(path="/product-section/save")
	public String onSubmitAddProduct(@ModelAttribute("command") Product product, Model model) {
		ResponseEntity<Product> products = template.postForEntity("lb://PRODUCT-SERVICE/save", product, Product.class);
		model.addAttribute("message" , products.getBody()) ; 
        return "add-product";
	}
	
	
	
	
	
	
	
	
	
	// ORDER CONTROLLERS 
	
	@GetMapping(path =  "/order-section")
	public String orderSection() {
		return "order-section"; 
	}
	
	@GetMapping(path="/order-section/orders")
	public String getOrders(Model model) {
	    ResponseEntity<Order[]> orders  = template.getForEntity("lb://ORDER-SERVICE/list", Order[].class) ;
		model.addAttribute("orders", orders.getBody());
		return "orders" ; 
	}
	
	@GetMapping(path="/order-section/orders/{username}")
	public String getOrdersByUser(@PathVariable("username") String username, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize, Model model) {
	    ResponseEntity<Order[]> orders  = template.getForEntity("lb://ORDER-SERVICE/list/" + username + Utility.pageQuery(page, pageSize)  , Order[].class) ;
	    if(orders.getBody() == null) {
	    	model.addAttribute("item" , "order") ; 
	    	return "shortage"; 	
	    }
	    else {
	    	model.addAttribute("orders", orders.getBody());
	    	orders  = template.getForEntity("lb://ORDER-SERVICE/list/" + username + Utility.pageQuery(page + 1, pageSize) , Order[].class);
	    	System.out.println(orders.getBody());
	    	model.addAttribute("pageNo", page);
	    	model.addAttribute("username", username); 
	    	model.addAttribute("hasNextPage", orders.getBody() != null); 
	    	model.addAttribute("nextPagePath", "/order-section/orders/" + username +  Utility.pageQuery(page + 1, pageSize) ); 
	    	model.addAttribute("hasPreviousPage" , page >= 1); 
	    	model.addAttribute("previousPagePath", "/order-section/orders/" + username +  Utility.pageQuery(page - 1, pageSize));
	    	model.addAttribute("pageSize", pageSize) ; 
			return "orders" ; 
	    }
	}
	
	@PostMapping(path="/order-section/orders-by-user")
	public String postOrdersByUser(@RequestParam("username") String username, @RequestParam("pageSize") int pageSize, Model model) { 
	    return getOrdersByUser(username, 0, pageSize, model); 
	}
}
