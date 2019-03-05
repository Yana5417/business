package com.business.dao;

import com.business.domain.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */
@Mapper
public interface CouponDAO {
    Coupon getCouponByCode(String couponCode);
}
