package com.apidevelopment.service;

import com.apidevelopment.entity.Sale;
import com.apidevelopment.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;


    // Add a sale
    public void addSale(Sale sale) {
        saleRepository.save(sale);
    }

    // Update a sale
    public void updateSale(int saleId, Sale updatedSale) {
        Optional<Sale> existingSale = saleRepository.findById(saleId);
        if (existingSale.isPresent()) {
            updatedSale.setId(saleId);
            saleRepository.save(updatedSale);
        } else {
            throw new IllegalArgumentException("Sale not found with ID: " + saleId);
        }
    }

    // Delete a sale
    public void deleteSale(int saleId) {
        Optional<Sale> existingSale = saleRepository.findById(saleId);
        if (existingSale.isPresent()) {
            saleRepository.deleteById(saleId);
        } else {
            throw new IllegalArgumentException("Sale not found with ID: " + saleId);
        }
    }

    // Get total revenue
//    public double getTotalRevenue() {
//        List<Sale> sales = saleRepository.findAll();
//        return sales.stream().mapToDouble(Sale::getPrice).sum();
//    }
//
//    // Get revenue by product
//    public double getRevenueByProduct(String productName) {
//        List<Sale> sales = saleRepository.findByProductName(productName);
//        return sales.stream().mapToDouble(Sale::getPrice).sum();
//    }
}


