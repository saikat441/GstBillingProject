package com.gstBillingProjectOwn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gstBillingProjectOwn.Entity.Product;
//import com.gstBillingProjectOwn.Entity.PurchaseInvoice;
import com.gstBillingProjectOwn.Entity.PurchaseInvoice;

public interface PurchaseInvoiceRepository extends JpaRepository<PurchaseInvoice, Long>{

}
