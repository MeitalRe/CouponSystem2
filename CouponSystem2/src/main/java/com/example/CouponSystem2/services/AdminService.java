package com.example.CouponSystem2.services;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.CouponSystem2.beans.Company;
import com.example.CouponSystem2.beans.Customer;
import com.example.CouponSystem2.exceptions.IllegalLoginException;
import com.example.CouponSystem2.exceptions.NotValidException;

import lombok.RequiredArgsConstructor;

@Service
@Scope("prototype")
@RequiredArgsConstructor
public class AdminService extends ClientService {
	private final String emailAdmin = "admin@email.com";
	private final String passwordAdmin = "admin";

	@Override
	
	public boolean login(String email, String password) throws IllegalLoginException {
		if (emailAdmin.equalsIgnoreCase("admin@email.com") && passwordAdmin.equalsIgnoreCase("admin")) {
			System.out.println("Welcome");
			return true;
		}
		throw new IllegalLoginException("Incorrect login details");
	}

	public void addCompany(Company company) throws NotValidException {
		for (Company company2 : companyRepository.findAll()) {

			if (company2.getName().equals(company.getName()))
				throw new NotValidException("Company name not valid");

			if (company2.getEmail().equals(company.getEmail()))
				throw new NotValidException("Company email not valid");

		}

		companyRepository.save(company);
	}

	public void updateCompany(Company company) throws NotValidException {
		for (Company comp : companyRepository.findAll()) {
			if (comp.getId() == company.getId() && comp.getName().equals(company.getName())) {
				companyRepository.saveAndFlush(company);
				System.out.println("Company updated !");
			}
			throw new NotValidException("Company could not be updated ");

		}

	}

	public void deleteCompany(Company company) {
		companyRepository.delete(company);
		System.out.println("Company deleted");

	}

	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	public Company getOneCompany(int id) {
		return companyRepository.getOne(id);
	}

	public void addCustomer(Customer customer) throws NotValidException {
		for (Customer cust : customerRepository.findAll()) {
			if (cust.getEmail().equals(customer.getEmail())) {
				throw new NotValidException("Already exists");
			}
		}
		customerRepository.save(customer);
		System.out.println("Customer added !");
	}

	public void updateCustomer(Customer customer) throws NotValidException {
		if (customer != null) {
			Customer cust = customerRepository.findCustomerById(customer.getId());
			if (cust != null) {
				customerRepository.saveAndFlush(customer);
				System.out.println("Customer Updated");
			} else {
				System.out.println("Customer " + customer.getId() + " Dosen't Exists");
			}
		}
	}

	public void deleteCustomer(Customer customer) {
		customerRepository.delete(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getOneCustomer(int id) {
		return customerRepository.getOne(id);
	}

}
