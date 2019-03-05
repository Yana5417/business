package com.business.service.impl;

import com.business.dao.BusinessCouponDAO;
import com.business.dao.CouponDAO;
import com.business.domain.BusinessCoupon;
import com.business.domain.Coupon;
import com.business.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */
@Service
public class CouponServiceImpl implements CouponService{

    @Autowired
    private CouponDAO couponDAO;
    @Autowired
    private BusinessCouponDAO businessCouponDAO;

    @Override
    public Coupon getCouponByCode(String couponCode) {
        return couponDAO.getCouponByCode(couponCode);
    }

    @Override
    public void exchangeCoupon(String couponCode, String businessId) {
        businessCouponDAO.exchangeCoupon(couponCode,businessId);
    }

    @Override
    public BusinessCoupon getCouponByBusinessIdAndCouponCode(String couponCode, String businessId) {
        return businessCouponDAO.getCouponByCodeAndBusinessId(couponCode,businessId);
    }
}
