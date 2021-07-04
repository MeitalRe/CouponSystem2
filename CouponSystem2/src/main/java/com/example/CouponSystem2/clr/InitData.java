package com.example.CouponSystem2.clr;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.CouponSystem2.beans.Category;
import com.example.CouponSystem2.beans.Company;
import com.example.CouponSystem2.beans.Coupon;
import com.example.CouponSystem2.beans.Customer;
import com.example.CouponSystem2.repos.CompanyRepository;
import com.example.CouponSystem2.repos.CouponRepository;
import com.example.CouponSystem2.repos.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Order(1)
public class InitData implements CommandLineRunner {
	private final CompanyRepository companyRepository;
	private final CustomerRepository customerRepository;
	private final CouponRepository couponRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Init Data");
		companyRepository.save(Company.builder().name("Cola").email("Coca@email.com").password("Cola").build());
		companyRepository.save(Company.builder().name("HP").email("Hp@email.com")
				.password("HP").build());
		companyRepository
				.save(Company.builder().name("Mac").email("Mac@email.com").password("Mac").build());
		companyRepository
				.save(Company.builder().name("Apple").email("Apple@email.com").password("Apple").build());
		companyRepository
				.save(Company.builder().name("Scorpion").email("Scorpion@email.com").password("Scorpion").build());

		customerRepository.save(Customer.builder().firstName("Adam").lastName("Adamy").email("Adam@email.com")
				.password("Adam").build());
		customerRepository.save(Customer.builder().firstName("Ben").lastName("Benben")
				.email("Ben@email.com").password("Ben").build());
		customerRepository.save(Customer.builder().firstName("Chen").lastName("Choen")
				.email("Chen@email.com").password("Chen").build());
		customerRepository.save(Customer.builder().firstName("Din").lastName("Dindin")
				.email("Din@email.com").password("Din").build());
		customerRepository.save(Customer.builder().firstName("Eli").lastName("Elison")
				.email("Eli@email.com").password("Eli").build());
		
		couponRepository.save(Coupon.builder().company_id(7).category_id(Category.COMPUTERS).title("MacBook").description( "Free MacBook")
				.startDate(LocalDate.of(2020, 10, 7)).endDate(LocalDate.of(2021, 10, 7)).amount(3).price(250.0).image("Image").build());
		couponRepository.save(Coupon.builder().company_id(8).category_id(Category.MOVIES).title("Movie").description( "Free admission to the Movie")
				.startDate(LocalDate.of(2020, 10, 8)).endDate(LocalDate.of(2021, 10, 8)).amount(10).price(50.0).image("Image").build());
		couponRepository.save(Coupon.builder().company_id(9).category_id(Category.MUSIC).title("Music").description( "Two concert tickets")
				.startDate(LocalDate.of(2020, 10, 9)).endDate(LocalDate.of(2021, 10, 9)).amount(12).price(80.0).image("Image").build());
		couponRepository.save(Coupon.builder().company_id(10).category_id(Category.VACATION).title("Vacation").description( "Couples vacation")
				.startDate(LocalDate.of(2020, 10, 10)).endDate(LocalDate.of(2021, 10, 10)).amount(5).price(150.0).image("Image").build());
		couponRepository.save(Coupon.builder().company_id(11).category_id(Category.ZOO).title("Zoo").description( "Free admission to the zoo")
				.startDate(LocalDate.of(2020, 10, 11)).endDate(LocalDate.of(2021, 10, 11)).amount(8).price(50.0).image("Image").build());

	}

}
