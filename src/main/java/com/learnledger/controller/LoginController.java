package com.learnledger.controller;

import com.learnledger.model.User;
import com.learnledger.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private UserService service;
    
    @GetMapping
    public String showLoginView(){
        return "login";
    }
    
    @PostMapping
    public String checkDetails(HttpSession session , @RequestParam("email") String email , @RequestParam("password") String password , @RequestParam("typeOfUser") String typeOfUser){
        
        if(typeOfUser.equals("user")){
            try{
                User user = service.findByEmailAndPassword(email, password);
                System.out.println(user);
                if(user != null){
                    session.setAttribute("user", user);
                    session.setAttribute("isUserLoggedIn", true);
                    return "redirect:/";
                }
                else{
                    return "login";
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        return "login";
    }
}
