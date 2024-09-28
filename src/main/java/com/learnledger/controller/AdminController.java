package com.learnledger.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController{
    
    @GetMapping("/{admin}")
    public Object showAdminPage(@PathVariable("admin") String admin , HttpServletResponse response){
        if(admin.equals("aditya")){
            return new ModelAndView("admin");
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