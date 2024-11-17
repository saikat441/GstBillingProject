package com.gstBillingProjectOwn.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gstBillingProjectOwn.DTO.ProductPurchaseDTO;
import com.gstBillingProjectOwn.Entity.Product;
import com.gstBillingProjectOwn.Entity.PurchaseInvoice;
//import com.gstBillingProjectOwn.Entity.PurchaseInvoice;
//import com.gstBillingProjectOwn.Entity.PurchaseInvoiceProduct;
import com.gstBillingProjectOwn.Repository.ProductRepository;
import com.gstBillingProjectOwn.Service.ProductService;
//import com.gstBillingProjectOwn.Repository.PurchaseInvoiceRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	
	@Autowired
    private ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
//	@Autowired
//	private PurchaseInvoiceRepository purchaseInvoiceRepository;
	
	//Request come from adminHome
	@GetMapping("/admin/addProductButton")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product()); 
        return "productAdd"; 
    }
	
	//Request come from adminHome
		@GetMapping("/admin/showProductsButton")
	    public String showProducts(Model model) {
			
			List<Product> products=productRepository.findAll();
			
	        model.addAttribute("products", products); 
	        return "productsShow"; 
	    }

	//Request come from productAdd.html
	@PostMapping("/do-addProduct")
    public String processAddProduct(@ModelAttribute Product product, Model model) {
        
        // Check if a product with the same code already exists
        if (productRepository.existsBypCode(product.getPCode())) {
            // Add error message to the model
            model.addAttribute("error", "Product code already exists. Please use a different product code.");
            return "productAdd";  // Return to the add product page with an error
        }
        
        // Save the new product to the database
        productRepository.save(product);
        
        // Add success message to the model
        model.addAttribute("success", "Product added successfully.");
        
        return "adminHome";  // Return to the admin home page
    }
	
	//Request come from userHome.html
	@GetMapping("/user/buyProductButton")
	public String showPurchaseProductPage(Model model) {
	    List<Product> products = productRepository.findAll();
	    model.addAttribute("products", products);
	    
//	    model.addAttribute("purchaseProduct", new PurchaseInvoice2());
	    return "userProductPurchase";
	}
	
	@GetMapping("/user/getProductDetails/{productId}")
	@ResponseBody
    public Product getProductDetails(@PathVariable Integer productId) {
        Product product = productRepository.getByPid(productId);
        
        // Map product details to a custom response
        return product;
    }
	
//	@PostMapping("/user/confirmPurchase")
//	public String confirmPurchase(
//	        @RequestParam String to,
//	        @RequestParam String address,
//	        @RequestParam String partyGstNo,
//	        @RequestParam String invoiceDate,
//	        @RequestParam("productList") String productListJson) {
//	    
//	    // Parse productListJson into a list of PurchaseItem details
//	    List<PurchaseInvoiceProduct> purchaseItems = parseProductListJson(productListJson);
//	    
//	    Date parsedInvoiceDate;
//        try {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            parsedInvoiceDate = dateFormat.parse(invoiceDate);
//        } catch (ParseException e) {
//            throw new RuntimeException("Failed to parse invoice date", e);
//        }
//
//	    // Create a new PurchaseInvoice and set its fields
//	    PurchaseInvoice invoice = new PurchaseInvoice();
//	    invoice.setToName(to);
//	    invoice.setAddress(address);
//	    invoice.setPartyGstNo(partyGstNo);
//	    invoice.setInvoiceDate(parsedInvoiceDate);
//	    invoice.setPurchaseItems(purchaseItems);
//
//	    // Set invoice reference in each PurchaseItem
//	    purchaseItems.forEach(item -> item.setPurchaseInvoice(invoice));
//
//	    // Save the invoice, which cascades and saves all associated purchase items
//	    purchaseInvoiceRepository.save(invoice);
//
//	    return "userHome";
//	}
//	
//	private List<PurchaseInvoiceProduct> parseProductListJson(String productListJson) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            // Convert JSON string to List<PurchaseItem>
//            return objectMapper.readValue(productListJson, new TypeReference<List<PurchaseInvoiceProduct>>() {});
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to parse product list JSON", e);
//        }
//
//	}	
	
	@PostMapping("/user/confirmPurchase")
    public String confirmPurchase(
            @RequestParam String to,
            @RequestParam String address,
            @RequestParam String partyGstNo,
            @RequestParam String invoiceDate,
            @RequestParam String productList, // JSON string of products
            Model model,HttpSession httpSession) {

        try {
            // Parse the JSON product list
            List<ProductPurchaseDTO> purchaseProducts = parseProductList(productList);

            // Create and populate invoice object
            PurchaseInvoice invoice = new PurchaseInvoice();
            invoice.setToName(to);
            invoice.setAddress(address);
            invoice.setPartyGstNo(partyGstNo);
//            invoice.setInvoiceDate(LocalDateTime.parse(invoiceDate));
           // invoice.setQuantity(purchaseProducts.size()); // Assuming the total quantity is product count



            // Save the invoice
            productService.savePurchaseInvoice(invoice, purchaseProducts,httpSession);

            model.addAttribute("success", "Purchase invoice created successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Error creating purchase invoice: " + e.getMessage());
        }

        return "userHome"; // Redirect back to purchase page
    }

    private List<ProductPurchaseDTO> parseProductList(String productList) {
    	ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Use Jackson to deserialize the JSON string into a List of Product objects
            return objectMapper.readValue(productList, new TypeReference<List<ProductPurchaseDTO>>(){});
        } catch (IOException e) {
            // Handle error (e.g., log it and return an empty list or throw a custom exception)
            e.printStackTrace();
            return List.of(); // Return an empty list in case of error
        }
    }
}
	

