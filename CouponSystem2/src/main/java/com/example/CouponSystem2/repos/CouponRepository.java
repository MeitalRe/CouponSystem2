package com.example.CouponSystem2.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CouponSystem2.beans.Category;
import com.example.CouponSystem2.beans.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	List<Coupon> findByCompanyId(int companyId);

	List<Coupon> findByCompanyIdAndPriceLessThan(int companyId, double maxPrice);

	List<Coupon> findByCompanyIdAndCategory(int companyId, Category category);

	int getPurchaseCouponByCouponIdAndCustomerId(int id, int customerId);

	void purchaseCoupon(int customerId, int id);

}
