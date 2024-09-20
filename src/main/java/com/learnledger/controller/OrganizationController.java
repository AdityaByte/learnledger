package com.learnledger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("org")
public class OrganizationController {
    
    @GetMapping("/form")
    @ResponseBody
    public String showOrgForm(){
        return "organization";
    }
    
}
