package com.business.utils;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.business.domain.BusinessDetail;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class BusinessContext {
    public static BusinessDetail getCurrentBusinessDetail(){
        return (BusinessDetail)getSession().getAttribute("businessDetail");
    }

    public static HttpSession getSession(){
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getSession();
    }

}
