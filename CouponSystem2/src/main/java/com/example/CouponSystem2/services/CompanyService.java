package com.example.CouponSystem2.services;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.CouponSystem2.beans.Company;
import com.example.CouponSystem2.beans.Coupon;
import com.example.CouponSystem2.exceptions.IllegalLoginException;
import com.example.CouponSystem2.exceptions.NotValidException;

import lombok.RequiredArgsConstructor;
@Service
@Scope("prototype")
@RequiredArgsConstructor
public class CompanyService extends ClientService {
	private final int companyId;

	@Override
	
	public boolean login(String email, String password) throws IllegalLoginException {
		Company company = companyRepository.getOne(companyId);
		if (company.getEmail().equals(email) || company.getPassword().equals(password)) {
			System.out.println("Welcome");
			return true;
		}
		throw new IllegalLoginException("Incorect email or password");

	}

	public void addCoupon(Coupon coupon) {
		List<Coupon> companyCoupons = companyRepository.getOne(companyId).getCoupons();
		for (int i = 0; i < companyCoupons.size(); i++) {
			if (companyCoupons.get(i).getTitle().equals(coupon.getTitle())
					&& companyCoupons.get(i).getCompany_id() == companyId) {
				System.out.println("Can not add coupon");

			} else {
				coupon.setCompany_id(companyId);
				companyCoupons.add(coupon);
				companyRepository.getOne(companyId).setCoupons(companyCoupons);
				companyRepository.saveAndFlush(companyRepository.getOne(companyId));
				System.out.println("Coupon successfully added");
				break;
			}

		}
		if (companyCoupons.size() == 0) {
			coupon.setCompany_id(companyId);
			companyCoupons.add(coupon);
			companyRepository.getOne(companyId).setCoupons(companyCoupons);
			companyRepository.saveAndFlush(companyRepository.getOne(companyId));
			System.out.println("Coupon successfully added");
		}

	}

	public void updateCompanyCoupon(Coupon coupon) throws NotValidException {
		List<Coupon> companyCoupons = companyRepository.getOne(companyId).getCoupons();

		for (Coupon coupon2 : companyCoupons) {
			if (coupon2.getId() != coupon.getId() || coupon2.getCompany_id() != coupon.getCompany_id()) {
				throw new NotValidException("Can not update coupon");
			}

		}
		couponRepository.saveAndFlush(coupon);
		System.out.println("Coupon successfully updated");
	}
	
	public void deleteCompanyCoupon(Coupon coupon) {
		couponRepository.delete(coupon);

	}
	
	public Coupon getCompanyCoupon(int id) {
		Coupon coupon = null;
		List<Coupon> companyCoupons = companyRepository.getOne(companyId).getCoupons();
		for (Coupon coupon2 : companyCoupons) {
			if (coupon2.getId() == id) {
				coupon = coupon2;
				break;
			}
		}
		return coupon;
	}
	
	
	   public List<Coupon> getCompanyCoupons() {
	        return couponRepository.findByCompanyId(companyId);
	    }

	    public List<Coupon> getCompanyOneCategoryCoupons(com.example.CouponSystem2.beans.Category category) {
	        return couponRepository.findByCompanyIdAndCategory(companyId,category);

	    }

	    public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) {
	        return couponRepository.findByCompanyIdAndPriceLessThan(companyId,maxPrice);
	    }

	    public Company getCompanyDetails() {
	        return companyRepository.getOne(companyId);
	    }



}