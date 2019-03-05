package com.business.dao;

import com.business.domain.BusinessDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */
@Mapper
public interface BusinessDetailDAO {
    void insertBusinessDetail(BusinessDetail businessDetail);
    BusinessDetail getBusinessDetailByPhone(@Param("businessPhone")String businessPhone);
    BusinessDetail getBusinessDetailByName(@Param("businessName")String businessName);

}
