package com.example.CouponSystem2.tasks;

import java.time.LocalDate;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.CouponSystem2.beans.Coupon;
import com.example.CouponSystem2.exceptions.ExpiredCouponException;
import com.example.CouponSystem2.repos.CouponRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DailyJob {
public final CouponRepository couponsRepository;
	
	
	
	@Scheduled(fixedRate = 1000)
	public void deleteExpiredCoupons() throws ExpiredCouponException {
		List<Coupon>getAllCoupons = couponsRepository.findAll();
		for (Coupon coupon : getAllCoupons) {
			if (coupon.getEndDate().isBefore(LocalDate.now())) {
				couponsRepository.delete(coupon);
				System.out.println("The old coupons have been deleted");
			}
		}
	}
}
