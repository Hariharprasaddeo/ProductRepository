package com.apidevelopment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_id", insertable = false, updatable = false)
    private int productId;
    private int quantity;
    @Column(name = "sale_date")
    private Date saleDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Sale(int id, int i) {
    }
}


