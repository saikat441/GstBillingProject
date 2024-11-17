package com.gstBillingProjectOwn.Entity;

import lombok.Data;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    private String productName;
    private String pCode; 
    private double price; 
    private double taxRate; 
    private int quantity;
    
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private Set<PurchaseInvoice> purchases;
//    
//    @ManyToOne
//    @JoinColumn(name = "purchase_invoice_id", nullable = false)
//    private PurchaseInvoice purchaseInvoice;
}

