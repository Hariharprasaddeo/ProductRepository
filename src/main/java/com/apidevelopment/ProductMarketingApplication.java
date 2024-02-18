package com.apidevelopment;


import com.apidevelopment.entity.Product;
import com.apidevelopment.entity.Sale;
import com.apidevelopment.service.ProductService;
import com.apidevelopment.service.SaleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.apidevelopment.entity.Product;
import com.apidevelopment.entity.Sale;
import com.apidevelopment.service.ProductService;
import com.apidevelopment.service.SaleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProductMarketingApplication {

	public static void main(String[] args) {
		// Run the Spring Boot application and get the application context
		ConfigurableApplicationContext context = SpringApplication.run(ProductMarketingApplication.class, args);

		// Autowire the ProductService and SaleService beans
		ProductService productService = context.getBean(ProductService.class);
		SaleService saleService = context.getBean(SaleService.class);

		// Create and add some Product objects
		Product product1 = new Product("Product 1", "Description 1", 10.99, 50);
		Product product2 = new Product("Product 2", "Description 2", 15.99, 30);
		productService.addProduct(product1);
		productService.addProduct(product2);

		// Create and add some Sale objects
		Sale sale1 = new Sale(product1.getId(), 5);
		Sale sale2 = new Sale(product2.getId(), 10);
		saleService.addSale(sale1);
		saleService.addSale(sale2);

		// Demonstrate revenue calculation methods
//		System.out.println("Total Revenue: " + productService.getTotalRevenue());
//		System.out.println("Revenue for Product 1: " + productService.getRevenueByProduct(product1.getId()));

		// Close the application context
		context.close();
	}
}
