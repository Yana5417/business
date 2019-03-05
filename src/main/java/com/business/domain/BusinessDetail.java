package com.business.domain;


/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */

public class BusinessDetail extends BaseDBEntity {
    private String businessId;
    private String businessName;
    private String businessPhone;
    private String businessAddressDetail;
    private String businessAddressProvince;
    private String businessAddressCity;
    private String businessAddressCounty;
    private String businessWechat;
    private String businessPassword;
    private String businessEmail;

    public String getBusinessPassword() {
        return businessPassword;
    }

    public void setBusinessPassword(String businessPassword) {
        this.businessPassword = businessPassword;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getBusinessAddressDetail() {
        return businessAddressDetail;
    }

    public void setBusinessAddressDetail(String businessAddressDetail) {
        this.businessAddressDetail = businessAddressDetail;
    }

    public String getBusinessAddressProvince() {
        return businessAddressProvince;
    }

    public void setBusinessAddressProvince(String businessAddressProvince) {
        this.businessAddressProvince = businessAddressProvince;
    }

    public String getBusinessAddressCity() {
        return businessAddressCity;
    }

    public void setBusinessAddressCity(String businessAddressCity) {
        this.businessAddressCity = businessAddressCity;
    }

    public String getBusinessAddressCounty() {
        return businessAddressCounty;
    }

    public void setBusinessAddressCounty(String businessAddressCounty) {
        this.businessAddressCounty = businessAddressCounty;
    }

    public String getBusinessWechat() {
        return businessWechat;
    }

    public void setBusinessWechat(String businessWechat) {
        this.businessWechat = businessWechat;
    }
}
