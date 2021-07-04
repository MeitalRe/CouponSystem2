package com.example.CouponSystem2.clr;



import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.CouponSystem2.beans.Company;

import com.example.CouponSystem2.services.AdminService;

import lombok.RequiredArgsConstructor;
@Component
@RequiredArgsConstructor
@Order(2)
public class AdminTest implements CommandLineRunner{
	public final AdminService adminService;
	@Override
	public void run(String... args) throws Exception {
			try {			
				adminService.login("admin@mail.co.il", "admim");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {			
				adminService.login("admin@email.com", "admin");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			
			Company company1 = Company.builder().name("Amazon").email("Amazon@email.com").password("Amazon").build();
			Company company2 = Company.builder().name("Volkswagen").email("Volkswagen@email.com").password("Volkswagen").build();
			Company company3 = Company.builder().name("Samsung Electronics").email("Samsung@email.com").password("Samsung").build();
			Company company4 = Company.builder().name("Microsoft").email("Microsoft@email.com").password("Microsoft").build();
			System.out.println(" Adding company ");
			adminService.addCompany(company1);
			adminService.addCompany(company2);
			adminService.addCompany(company3);
			adminService.addCompany(company4);
			System.out.println("Cant add company");
			try {
				adminService.addCompany(company1);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Get all companies");
			System.out.println(adminService.getAllCompanies());
			Company company6 = adminService.getOneCompany(1);
			System.out.println("Update company");
			company6.setEmail("Vova@email.com");
			adminService.updateCompany(company6);
			System.out.println(adminService.getOneCompany(1));
			System.out.println(" Can not update company");
			company6.setName("Vova");
			try {
				adminService.updateCompany(company6);
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println("Get one company");
			System.out.println(adminService.getOneCompany(2));
			System.out.println("Delete company");
			adminService.deleteCompany(company4);
			System.out.println("Get all companies");
			System.out.println(adminService.getAllCompanies());
			System.out.println();
		
	}

}
