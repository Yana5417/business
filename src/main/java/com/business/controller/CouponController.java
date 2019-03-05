package com.business.controller;

import com.business.annotation.CheckLogin;
import com.business.domain.BusinessCoupon;
import com.business.domain.BusinessDetail;
import com.business.domain.Coupon;
import com.business.domain.ResResult;
import com.business.domain.ResultCode;
import com.business.service.CouponService;
import com.business.utils.BusinessContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */
@RestController
public class CouponController {
    @Autowired
    private CouponService couponService;
    @PostMapping("/business/coupon/exchange")
    @CheckLogin
    public ResResult exchangeCoupon(@RequestParam("couponCode")String couponCode){
        Coupon coupon = couponService.getCouponByCode(couponCode);
        if(coupon == null){
            return new ResResult(ResultCode.PARAM_INVALID,"卡券不存在");
        }

        Long now = System.currentTimeMillis();
        if(now<coupon.getStartTime().getTime()){
            return new ResResult(ResultCode.PARAM_INVALID,"卡券未生效");
        }
        if(now>coupon.getEndTime().getTime()){
            return new ResResult(ResultCode.PARAM_INVALID,"卡券已过期");
        }
        BusinessDetail currentBusinessDetail = BusinessContext.getCurrentBusinessDetail();

        BusinessCoupon businessCoupon = couponService.getCouponByBusinessIdAndCouponCode(couponCode,currentBusinessDetail.getBusinessId());
        if(businessCoupon != null){
            return new ResResult(ResultCode.PARAM_INVALID,"卡券已兑换");
        }
        couponService.exchangeCoupon(couponCode,currentBusinessDetail.getBusinessId());
        return new ResResult(ResultCode.SUCCESS);


    }
}
