package com.learnledger.controller;

import com.learnledger.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountHandler {
    
    @GetMapping
    public String userHandler(HttpSession session , Model model){
        
        Boolean isUserLoggedIn = (Boolean)session.getAttribute("isUserLoggedIn");
        
        if(Boolean.TRUE.equals(isUserLoggedIn)){
            
            User user = (User) session.getAttribute("user");
            if(user!=null){
                model.addAttribute("userData", user);
            }
            return "account";
        }
        else{
            return "redirect:/";
        }
    }
    
}
