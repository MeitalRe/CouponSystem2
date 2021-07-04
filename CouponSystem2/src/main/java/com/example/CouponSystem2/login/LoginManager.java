package com.example.CouponSystem2.login;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.CouponSystem2.exceptions.IllegalLoginException;
import com.example.CouponSystem2.services.AdminService;
import com.example.CouponSystem2.services.CompanyService;
import com.example.CouponSystem2.services.CustomerService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class LoginManager {
	private final ApplicationContext ctx;
	
	public Object ClientService(String email, String password, ClientType clientType) throws IllegalLoginException {
		switch (clientType) {
		case ADMINISTRATOR:
			if (email.equals("admin@email.com") && password.equals("admin")) {
				System.out.println("Welcome ADMINISTRATOR"+email);
				return new AdminService();
			}
			System.out.println("Incorrect login details");
			return null;
		case COMPANY:
		        if (ctx.getBean(CompanyService.class).login(email, password)) {
		            System.out.println("Welcome COMPANY"+email);
		            return ctx.getBean(CompanyService.class);
		        }
		        System.out.println("Incorrect login details");
		        return null;
		case CUSTOMER:
		        if (ctx.getBean(CustomerService.class).login(email, password)) {
		            System.out.println("Welcome CUSTOMER"+email);
		            return ctx.getBean(CustomerService.class);
		        }
		        System.out.println("Incorrect login details");
		        return null;
	}
		return null;

	}

}
