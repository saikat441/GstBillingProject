package com.gstBillingProjectOwn.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "purchase_invoices")
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInvoice{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId; // Unique invoice identifier

    @ManyToOne
    @JoinColumn(name = "u_id", nullable = false)
    private User user; // User associated with this invoice

    @ManyToMany
    @JoinTable(
        name = "invoice_products", // Join table for many-to-many relationship
        joinColumns = @JoinColumn(name = "invoice_id"),
        inverseJoinColumns = @JoinColumn(name = "pid")
    )
    private List<Product> products; // List of products in the invoice

    private String toName; // Name of the party to whom the invoice is addressed

    private String address; // Address of the party

    private String partyGstNo; // GST number of the party

    private int quantity; // Total quantity of products in the invoice

    private double cgst; // Central GST amount

    private double sgst; // State GST amount

    private double totalPrice; // Total price of the invoice (including taxes)

    private LocalDateTime invoiceDate; // Date and time of invoice generation
}
