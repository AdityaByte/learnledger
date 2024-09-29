package com.learnledger.controller;

import com.learnledger.model.Organization;
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
            
            Object currentUser = (Object) session.getAttribute("currentUser");
            if(currentUser instanceof User){
                User user = (User) currentUser;
                model.addAttribute("userData", user);
            }
            else if(currentUser instanceof Organization){
                Organization org = (Organization) currentUser;
                model.addAttribute("orgData", org);
            }
            return "account";
        }
        else{
            return "redirect:/";
        }
    }
    
}
