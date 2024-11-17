package com.gstBillingProjectOwn.Entity;


import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uId;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNo;

    private String gstNo;

    private String address;

    private String password;

    private String shopType;
    
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<PurchaseInvoice> purchases;
//    
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<PurchaseInvoice> purchaseInvoices;
    
//    @ManyToOne
//    @JoinColumn(name = "u_id", nullable = false)
//    private User user;
//    
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "purchase_invoice_id") // Foreign key in Product table
//    private List<Product> products;
}

