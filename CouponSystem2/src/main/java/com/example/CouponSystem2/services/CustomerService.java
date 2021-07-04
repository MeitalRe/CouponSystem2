package com.example.CouponSystem2.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.CouponSystem2.beans.Category;
import com.example.CouponSystem2.beans.Coupon;
import com.example.CouponSystem2.beans.Customer;
import com.example.CouponSystem2.exceptions.IllegalLoginException;
import com.example.CouponSystem2.exceptions.NotValidException;

import lombok.RequiredArgsConstructor;

@Service
@Scope("prototype")
@RequiredArgsConstructor
public class CustomerService extends ClientService {
	private int customerId;

	@Override
	
	public boolean login(String email, String password) throws IllegalLoginException {

		Customer customer = customerRepository.getOne(customerId);
		if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
			System.out.println("Welcome ");
			return true;
		}
		throw new IllegalLoginException("Incorrect login details");
	}

	public void purchaseCoupon(com.example.CouponSystem2.beans.Coupon coupon) throws NotValidException {
		if (coupon.getEndDate().isBefore(LocalDate.now())) {
			throw new NotValidException("The coupon has expired");
		}
		if (couponRepository.getPurchaseCouponByCouponIdAndCustomerId(coupon.getId(), customerId) > 0) {
			throw new NotValidException("The coupon already exists");

		}
		couponRepository.purchaseCoupon(customerId, coupon.getId());
	}
	
	public List<Coupon> getCoupons() {
		return customerRepository.getOne(customerId).getCoupons();
	}
	
	public List<Coupon> getCouponsByCategory(Category category) {
		List<Coupon> customerCoupons = customerRepository.getOne(customerId).getCoupons();
		List<Coupon>couponsByCategory = new ArrayList<>();
		for (Coupon coupon : customerCoupons) {
			if (coupon.getCategory_id().equals(category)) {
				couponsByCategory.add(coupon);
			}
		}
		return couponsByCategory;
	}
	public List<Coupon> getCouponsByMaxPrice(double maxPrice) {
		List<Coupon> customerCoupons = customerRepository.getOne(customerId).getCoupons();
		List<Coupon>couponsByMaxPrice = new ArrayList<>();
		for (Coupon coupon : customerCoupons) {
			if (coupon.getPrice() < maxPrice) {
				couponsByMaxPrice.add(coupon);
			}
		}
		return couponsByMaxPrice;
	}
	
	public Customer getCustomerDetails() {
		return customerRepository.getOne(customerId);
	}

	public void setCompanyId(int i) {
		// TODO Auto-generated method stub
		
	}

}
