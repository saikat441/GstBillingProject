package com.gstBillingProjectOwn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gstBillingProjectOwn.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	boolean existsBypCode(String pCode);

	Product getByPid(Integer productId);

}
