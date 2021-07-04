package com.example.CouponSystem2.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.CouponSystem2.exceptions.IllegalLoginException;
import com.example.CouponSystem2.repos.CompanyRepository;
import com.example.CouponSystem2.repos.CouponRepository;
import com.example.CouponSystem2.repos.CustomerRepository;

public abstract class ClientService {
	@Autowired
	protected CompanyRepository companyRepository;
	@Autowired
	protected CustomerRepository customerRepository;
	@Autowired
	protected CouponRepository couponRepository;
	
	abstract boolean login(String email, String password)throws IllegalLoginException;
}

