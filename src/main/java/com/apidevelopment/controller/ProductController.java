package com.apidevelopment.controller;

import com.apidevelopment.entity.Product;
import com.apidevelopment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        updatedProduct.setId(id);
        Product product = productService.updateProduct(updatedProduct);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
             productService.deleteProductById(id);
        return ResponseEntity.ok("Product with ID " + id + " is deleted");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/revenue/total")
    public ResponseEntity<Double> getTotalRevenue() {
        double totalRevenue = productService.getTotalRevenue();
        return new ResponseEntity<>(totalRevenue, HttpStatus.OK);
    }

//    @GetMapping("/revenue/{productId}")
//    public ResponseEntity<Double> getRevenueByProduct(@PathVariable int productId) {
//        double revenue = productService.getRevenueByProduct(productId);
//        return new ResponseEntity<>(revenue, HttpStatus.OK);
//    }
    @GetMapping("/allProducts")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "2") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.getAllProducts(pageable);
        return ResponseEntity.ok().body(products);
    }
}
