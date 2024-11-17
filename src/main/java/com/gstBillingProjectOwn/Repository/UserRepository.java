package com.gstBillingProjectOwn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gstBillingProjectOwn.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByEmail(String email);

	User findByEmail(String email);

}
