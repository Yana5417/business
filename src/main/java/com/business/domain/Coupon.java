package com.business.domain;

import java.util.Date;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */

public class Coupon extends BaseDBEntity {
    String couponCode;
    private Date startTime;
    private Date endTime;
    private String cardMsg;

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCardMsg() {
        return cardMsg;
    }

    public void setCardMsg(String cardMsg) {
        this.cardMsg = cardMsg;
    }


}
