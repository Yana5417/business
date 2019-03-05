package com.business.service;

import com.business.domain.BusinessDetail;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */

public interface BusinessDetailService {
    BusinessDetail getBussinessDetailByName(String businessName);

    void insertBusinessDetail(BusinessDetail businessDetail);

    BusinessDetail getBusinessDetailByPhone(String phone);
}
