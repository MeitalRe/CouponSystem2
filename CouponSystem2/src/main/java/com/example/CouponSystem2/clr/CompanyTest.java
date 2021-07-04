package com.example.CouponSystem2.clr;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.CouponSystem2.beans.Category;
import com.example.CouponSystem2.beans.Coupon;
import com.example.CouponSystem2.repos.CouponRepository;
import com.example.CouponSystem2.services.CompanyService;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
@Order(3)
public class CompanyTest implements CommandLineRunner{
	public final CompanyService companyService;
	public final CouponRepository couponRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		companyService.login("Amazon@email.com", "Amazon");
		Coupon coupon1= Coupon.builder().company_id(2).category_id(Category.MUSIC).title("Sony").description(" New Headset")
				.startDate(LocalDate.of(2020, 10, 2)).endDate(LocalDate.of(2021, 10, 2)).amount(5).price(38.0).image("Image").build();
		Coupon coupon2= Coupon.builder().company_id(3).category_id(Category.COMPUTERS).title("Dell").description("New Dell Screen")
				.startDate(LocalDate.of(2020, 10, 3)).endDate(LocalDate.of(2021, 10, 3)).amount(5).price(42.0).image("Image").build();
		Coupon coupon3= Coupon.builder().company_id(4).category_id(Category.MOVIES).title("Movie").description(" Two tickets to the movie")
				.startDate(LocalDate.of(2020, 10, 4)).endDate(LocalDate.of(2021, 10, 4)).amount(5).price(83.0).image("Image").build();
		Coupon coupon4= Coupon.builder().company_id(5).category_id(Category.VACATION).title("Vacation").description("A vacation in the Caribbean")
				.startDate(LocalDate.of(2020, 10, 5)).endDate(LocalDate.of(2021, 10, 5)).amount(8).price(150.0).image("Image").build();
		Coupon coupon5= Coupon.builder().company_id(6).category_id(Category.ZOO).title("Zoo").description( "Free admission to the zoo")
				.startDate(LocalDate.of(2020, 10, 6)).endDate(LocalDate.of(2021, 10, 6)).amount(10).price(50.0).image("Image").build();
		couponRepository.saveAll(Arrays.asList(coupon1,coupon2,coupon3,coupon4,coupon5));
		companyService.addCoupon(coupon1);
		companyService.addCoupon(coupon1);
		System.out.println();
		System.out.println(	companyService.getCompanyDetails());
		System.out.println(companyService.getCompanyCoupons());
		companyService.deleteCompanyCoupon(companyService.getCompanyCoupon(3));
		try {
			companyService.getCompanyCoupon(2).setAmount(8);
			companyService.updateCompanyCoupon(companyService.getCompanyCoupon(2));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		 try {
			 companyService.getCompanyCoupon(2).setId(8);
			 companyService.updateCompanyCoupon(companyService.getCompanyCoupon(2));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		 System.out.println(companyService.getCompanyCoupon(2));
		 System.out.println(companyService.getCompanyCouponsByMaxPrice(150));
		
		
	}

}
