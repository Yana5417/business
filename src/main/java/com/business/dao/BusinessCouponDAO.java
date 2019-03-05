package com.business.dao;

import com.business.domain.BusinessCoupon;
import com.business.domain.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */
@Mapper
public interface BusinessCouponDAO {
    BusinessCoupon getCouponByCodeAndBusinessId(@Param("couponCode") String couponCode, @Param("businessId") String businessId);

    void exchangeCoupon(@Param("couponCode")String couponCode, @Param("businessId") String businessId);
}
