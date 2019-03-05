package com.business.domain;


/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */

public class BusinessCoupon extends BaseDBEntity {
    private String couponCode;
    private String businessId;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
}
