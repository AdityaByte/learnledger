package com.learnledger.controller;

import com.learnledger.enums.UserType;
import com.learnledger.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController{
    
    @Autowired
    private UserService service;
    
    @GetMapping("/{admin}")
    public Object showAdminPage(@PathVariable("admin") String admin , HttpServletResponse response){
        if(admin.equals("aditya")){
            Long users = service.countByUserType(UserType.USER);
            System.out.println(users);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("users" , users);
            modelAndView.setViewName("admin");
            return modelAndView;
        }
        else{
            response.setContentType("text/html");
            try{
                response.getWriter().write("<h1>You cant access the admin page</h1>");
            }
            catch(IOException e){
                e.printStackTrace();
            }
            return null;
        }
    }
    
}