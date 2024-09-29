package com.learnledger.controller;

import com.learnledger.enums.UserType;
import com.learnledger.model.Organization;
import com.learnledger.model.User;
import com.learnledger.service.OrganizationService;
import com.learnledger.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private UserService service;
    
    @Autowired
    private OrganizationService orgService;
    
    @GetMapping
    public String showLoginView(){
        return "login";
    }
    
    @PostMapping
    @ResponseBody
    public ResponseEntity<Map<String , String>> checkDetails(HttpSession session , @RequestBody Map<String , String> loginData){
        System.out.println("controller is running");
        Map<String , String> response = new HashMap<>();
        
        String email = loginData.get("email");        
        String password = loginData.get("password");
        String typeofUser = loginData.get("typeofUser");
        
        System.out.println(email + " " + password + " " + typeofUser);
        
        if(UserType.USER == UserType.valueOf(typeofUser)){
            try{
                User user = service.findByEmailAndPassword(email, password);
                if(user != null){
                    session.setAttribute("currentUser", user);
                    session.setAttribute("isUserLoggedIn", true);
                    response.put("status", "success");
                }
                else{
                    response.put("status" , "failure");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else if(UserType.ORGANIZATION == UserType.valueOf(typeofUser)){
            try{
                Organization organization = orgService.findByCredentials(email, password);
                if(organization != null){
                    session.setAttribute("currentUser", organization);
                    session.setAttribute("isUserLoggedIn", true);
                    response.put("status", "success");
                }
                else{
                    response.put("status", "failure");
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            response.put("error", "error");
        }
        
        
        return ResponseEntity.ok(response);
    }
}
