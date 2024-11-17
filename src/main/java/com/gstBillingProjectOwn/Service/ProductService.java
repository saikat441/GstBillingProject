package com.gstBillingProjectOwn.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gstBillingProjectOwn.DTO.ProductPurchaseDTO;
import com.gstBillingProjectOwn.Entity.Product;
import com.gstBillingProjectOwn.Entity.PurchaseInvoice;
import com.gstBillingProjectOwn.Entity.User;
import com.gstBillingProjectOwn.Repository.ProductRepository;
import com.gstBillingProjectOwn.Repository.PurchaseInvoiceRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	PurchaseInvoiceRepository purchaseInvoiceRepository;

	public PurchaseInvoice savePurchaseInvoice(PurchaseInvoice invoice, List<ProductPurchaseDTO> purchaseProducts,HttpSession httpSession) {
		// TODO Auto-generated method stub
		
		
		User user = (User) httpSession.getAttribute("user");
		
		invoice.setUser(user);
        invoice.setInvoiceDate(LocalDateTime.now());

        // Extract product IDs
        List<Integer> productIds = purchaseProducts.stream()
                .map(ProductPurchaseDTO::getProductId)
                .collect(Collectors.toList());

        // Fetch products by IDs
        List<Product> products = productRepository.findAllById(productIds);
        invoice.setProducts(products);

        // Calculate totals
        double totalCgst = purchaseProducts.stream()
                .mapToDouble(p->p.getCGST())
                .sum();

        double totalSgst = purchaseProducts.stream()
                .mapToDouble(p -> p.getSGST())
                .sum();

        double totalPrice = purchaseProducts.stream()
                .mapToDouble(p -> p.getTotalPrice())
                .sum();
        
        int totalQuantity = (int) purchaseProducts.stream().mapToDouble(p -> p.getQuantity()).sum();
        		

        invoice.setCgst(totalCgst);
        invoice.setSgst(totalSgst);
        invoice.setTotalPrice(totalPrice);
        invoice.setQuantity(totalQuantity);

        return purchaseInvoiceRepository.save(invoice);
		
	}

}
