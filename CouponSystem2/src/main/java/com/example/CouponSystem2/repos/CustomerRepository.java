package com.example.CouponSystem2.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CouponSystem2.beans.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Customer findCustomerById(int id);

	boolean existsByEmailAndPassword(String email, String password);

	boolean isCustomerExist(String email, String password);

	Object getCustomerByEmailAndPassword(String email, String password);

}
