package com.business.service.impl;

import com.business.dao.BusinessDetailDAO;
import com.business.domain.BusinessDetail;
import com.business.service.BusinessDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */
@Service
public class BusinessDetailServiceImpl implements BusinessDetailService {
    @Autowired
    private BusinessDetailDAO businessDetailDAO;

    @Override
    public BusinessDetail getBussinessDetailByName(String businessName) {
        return businessDetailDAO.getBusinessDetailByName(businessName);
    }

    @Override
    public void insertBusinessDetail(BusinessDetail businessDetail) {
        businessDetailDAO.insertBusinessDetail(businessDetail);
    }

    @Override
    public BusinessDetail getBusinessDetailByPhone(String phone) {
        return businessDetailDAO.getBusinessDetailByPhone(phone);
    }

}
