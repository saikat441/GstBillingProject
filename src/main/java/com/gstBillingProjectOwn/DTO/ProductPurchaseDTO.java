package com.gstBillingProjectOwn.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


public class ProductPurchaseDTO {
	private Integer productId;
    private String productName;
    private String pCode;
    private double price;
    private double taxRate;
    private int quantity;
    
    @JsonProperty("CGST")
    private double CGST;
    
    @JsonProperty("SGST")
    private double SGST;
    private double totalPrice;
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getCGST() {
		return CGST;
	}
	public void setCGST(double cGST) {
		CGST = cGST;
	}
	public double getSGST() {
		return SGST;
	}
	public void setSGST(double sGST) {
		SGST = sGST;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
    
    
}
