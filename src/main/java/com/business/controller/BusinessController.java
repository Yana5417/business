package com.business.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aliyuncs.exceptions.ClientException;
import com.business.domain.BusinessDetail;
import com.business.domain.ResResult;
import com.business.domain.ResultCode;
import com.business.service.BusinessDetailService;
import com.business.utils.CommonPropContainer;
import com.business.utils.CryptUtils;
import com.business.utils.RandomUtil;
import com.business.utils.SmsUtil;
import com.business.utils.ValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lxliuxuan
 * Date: 2019/01/12
 */
@RestController
@RequestMapping("/business")
public class BusinessController {
    private Logger logger = LoggerFactory.getLogger(BusinessController.class);
    private static Map<String,String> map =new HashMap<>();
    @Autowired
    private BusinessDetailService businessDetailService;
    @GetMapping("/img/code")
    public ResResult getImgCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession();

        ValidateCode vCode = new ValidateCode(120,40,5,100);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
        return new ResResult(ResultCode.SUCCESS);
    }

    @GetMapping("/sms/code")
    public ResResult getSmsCode(
                                @RequestParam("phone")String phone,
                                @RequestParam("code")String code,
                                HttpServletRequest request){
        HttpSession session = request.getSession();
        String code1 = (String)session.getAttribute("code");
        if(code1 == null || !code.equals(code1)){
            return new ResResult(ResultCode.PARAM_INVALID,"图形验证码不正确");
        }
        if(!checkPhone(phone)){
            return new ResResult(ResultCode.PARAM_INVALID,"手机号不正确");
        }
        String smsCode = RandomUtil.getRandomNumber(6);
        session.setAttribute("smdCode",smsCode);
        try {
            map.put(phone,smsCode);
            SmsUtil.sendSms(smsCode, phone);
        } catch (ClientException e) {
            logger.error("send sms error,code:{},phone:{}",code,phone);
        }
        return new ResResult(ResultCode.SUCCESS,smsCode);

    }
    @PostMapping("/login")
    public ResResult login(@RequestParam("phone")String phone,
                           @RequestParam("password")String password,
                           @RequestParam("code")String code,
                           HttpServletRequest request){
        HttpSession session = request.getSession();

        String code1 = (String)session.getAttribute("code");
        logger.info("code1:"+code1);
        logger.info("code:"+code);

        if(code1 == null || !code.equals(code1)){
            return new ResResult(ResultCode.PARAM_INVALID,"图形验证码不正确");
        }

        BusinessDetail businessDetail = businessDetailService.getBusinessDetailByPhone(phone);
        if(businessDetail == null){
            return new ResResult(ResultCode.PARAM_INVALID,"用户名或密码不正确");
        }
        if(!checkPassword(password,businessDetail.getBusinessPassword())){
            
            return new ResResult(ResultCode.PARAM_INVALID,"用户名或密码不正确");

        }
        session.setAttribute("businessDetail",businessDetail);
        return new ResResult(ResultCode.SUCCESS);

    }

    private boolean checkPassword(String password, String businessPassword) {
        return password.equals(CryptUtils.decryptDES(businessPassword,CommonPropContainer.getPasswordKey()));
    }


    @PostMapping("/register")
    public ResResult registerBusiness(@RequestParam("businessName")String businessName,
                                      @RequestParam("password")String password,
                                      @RequestParam("rePassword")String rePassword,
                                      @RequestParam("phone")String phone,
                                      @RequestParam("email")String email,

                                      @RequestParam("phoneCode")String phoneCode,
                                      @RequestParam("businessAddressDetail")String businessAddressDetail,
                                      @RequestParam("businessAddressProvince")String businessAddressProvince,
                                      @RequestParam("businessAddressCounty")String businessAddressCounty,

                                      @RequestParam("businessAddressCity")String businessAddressCity,
                                      @RequestParam("wechat")String businessWechat
                                      ){
        if(!checkSmsCode(phone,phoneCode)){
            return new ResResult(ResultCode.PARAM_INVALID,"手机验证码不正确");
        }
        map.remove(phone);
        if(!password.equals(rePassword)){
            return new ResResult(ResultCode.PARAM_INVALID,"两次密码不一致");
        }
        BusinessDetail businessDetail = businessDetailService.getBussinessDetailByName(businessName);
        if(businessDetail != null){
            return new ResResult(ResultCode.PARAM_INVALID,"商户名已存在");
        }
        businessDetail = businessDetailService.getBusinessDetailByPhone(phone);
        if(businessDetail != null){
            return new ResResult(ResultCode.PARAM_INVALID,"用户手机号已存在");
        }
        businessDetail = new BusinessDetail();
        businessDetail.setBusinessPassword(CryptUtils.encryptDES(password,CommonPropContainer.getPasswordKey()));
        businessDetail.setBusinessPhone(phone);
        businessDetail.setBusinessEmail(email);
        businessDetail.setBusinessAddressCity(businessAddressCity);
        businessDetail.setBusinessAddressCounty(businessAddressCounty);
        businessDetail.setBusinessAddressDetail(businessAddressDetail);
        businessDetail.setBusinessId(RandomUtil.getRandomChallenge(20));
        businessDetail.setBusinessAddressProvince(businessAddressProvince);
        businessDetail.setBusinessWechat(businessWechat);
        businessDetail.setBusinessName(businessName);
        businessDetailService.insertBusinessDetail(businessDetail);
        return new ResResult(ResultCode.SUCCESS);

    }

    private boolean checkSmsCode(String phone, String code) {
        String s = map.get(phone);

        return code.equals(s);
    }

    private boolean checkPhone(String phone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(phone.length() != 11){
            return false;
        }

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();

        return isMatch;
    }

}
