package com.example.CouponSystem2.clr;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.CouponSystem2.beans.Category;
import com.example.CouponSystem2.repos.CouponRepository;
import com.example.CouponSystem2.services.CustomerService;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
@Order(4)
public class CustomerTest implements CommandLineRunner {
	public final CustomerService customerService;
	public final CouponRepository couponsRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Customer Test");
		customerService.setCompanyId(1);
		customerService.login("James@email.com", "James");
		try {

			customerService.purchaseCoupon(couponsRepository.getOne(2));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		customerService.purchaseCoupon(couponsRepository.getOne(4));

		try {

			customerService.purchaseCoupon(couponsRepository.getOne(4));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {

			customerService.purchaseCoupon(couponsRepository.getOne(6));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(customerService.getCoupons());
		System.out.println(customerService.getCouponsByCategory(Category.VACATION));
	}


}
