package com.business.service;

import com.business.domain.BusinessCoupon;
import com.business.domain.Coupon;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */
public interface CouponService {
    Coupon getCouponByCode(String couponCode);

    void exchangeCoupon(String couponCode, String businessId);

    BusinessCoupon getCouponByBusinessIdAndCouponCode(String couponCode, String businessId);
}
