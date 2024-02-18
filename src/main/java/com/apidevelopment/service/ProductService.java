package com.apidevelopment.service;

import com.apidevelopment.entity.Product;
import com.apidevelopment.entity.Sale;
import com.apidevelopment.repository.ProductRepository;
import com.apidevelopment.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private List<Product> productList;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleRepository saleRepository;

    public ProductService() {
        this.productList = productList;
    }

    // Method to add a new product
    public Product addProduct(Product product) {
        // Check if a product with the same name already exists
        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if (existingProduct.isPresent()) {
            throw new IllegalArgumentException("Product with name " + product.getName() + " already exists");
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Product updatedProduct) {
        // Check if the product exists
        int productId = updatedProduct.getId();
        if (!productRepository.existsById(productId)) {
            throw new IllegalArgumentException("Product with ID " + productId + " not found");
        }
        return productRepository.save(updatedProduct);
    }
    public void deleteProductById(int id) {
        Optional<Product> byId = productRepository.findById(id);

        if(byId.isPresent()) {
            productRepository.deleteById(id);
        }else {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }
    }

    public List<Product> getAllProducts() {
        List<Product> all = productRepository.findAll();
        return all;
    }

    public Product getProductById(int id) {

        Product product = productRepository.findById(id).get();
        return product; // Return null if product with given ID is not found
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

//    public double getTotalRevenue() {
//        double totalRevenue = 0.0;
//        for (Product product : productList) {
//            for (Sale sale : product.getSales()) {
//                totalRevenue += sale.getQuantity() * product.getPrice();
//            }
//        }
//        return totalRevenue;
//    }
//
//    public double getRevenueByProduct(int productId) {
//        double revenue = 0.0;
//        Product product = getProductById(productId);
//        if (product != null) {
//            for (Sale sale : product.getSales()) {
//                revenue += sale.getQuantity() * product.getPrice();
//            }
//        }
//        return revenue;
//    }

    public double getTotalRevenue() {
        List<Sale> sales = saleRepository.findAll();
        double totalRevenue = 0;
        for (Sale sale : sales) {
            Product product = productRepository.findById(sale.getProductId()).orElse(null);
            if (product != null) {
                totalRevenue += product.getPrice() * sale.getQuantity();
            }
        }
        return totalRevenue;
    }

//    public double getRevenueByProduct(int productId) {
//        Product product = productRepository.findById(productId).orElse(null);
//        if (product != null) {
//            List<Sale> sales = product.getSales();
//            double revenue = 0;
//            for (Sale sale : sales) {
//                revenue += sale.getQuantity() * product.getPrice();
//            }
//            return revenue;
//        } else {
//            return 0; // Or throw exception
//        }
//    }
}
