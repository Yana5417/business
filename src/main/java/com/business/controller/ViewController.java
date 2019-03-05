package com.business.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: lxliuxuan
 * Date: 2018/11/06
 */
@Controller
public class ViewController {
    @GetMapping("/register")
    public String register(){
        return "register";

    }
    @GetMapping("/login")
    public String login(){
        return "login";

    }
    @GetMapping("/code")
    public String code(){
        return "code";

    }
}
