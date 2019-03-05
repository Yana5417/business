package com.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lxliuxuan
 * Date: 2018/11/06
 */
@RequestMapping("/system")
@RestController
public class SystemController {
    @GetMapping("/status")
    public String status(){
        return "success";
    }

}
