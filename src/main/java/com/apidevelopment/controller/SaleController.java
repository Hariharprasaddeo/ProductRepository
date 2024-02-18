package com.apidevelopment.controller;

import com.apidevelopment.entity.Sale;
import com.apidevelopment.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sales")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // Endpoint to add a sale
    @PostMapping("addSale")
    public ResponseEntity<String> addSale(@RequestBody Sale sale) {
        try {
            saleService.addSale(sale);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sale added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Endpoint to update a sale
    @PutMapping("/{saleId}")
    public ResponseEntity<String> updateSale(@PathVariable int saleId, @RequestBody Sale updatedSale) {
        try {
            saleService.updateSale(saleId, updatedSale);
            return ResponseEntity.status(HttpStatus.OK).body("Sale updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Endpoint to delete a sale
    @DeleteMapping("/{saleId}")
    public ResponseEntity<String> deleteSale(@PathVariable int saleId) {
        try {
            saleService.deleteSale(saleId);
            return ResponseEntity.status(HttpStatus.OK).body("Sale deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
