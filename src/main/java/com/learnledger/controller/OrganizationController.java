package com.learnledger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("org")
public class OrganizationController {
    
    @GetMapping("/form")
    public String showOrgForm(){
        return "organization";
    }
    
}
