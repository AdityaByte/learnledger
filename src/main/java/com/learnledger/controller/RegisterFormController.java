package com.learnledger.controller;

import com.learnledger.model.User;
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
@RequestMapping(value = "/register")
public class RegisterFormController {
    
    private final User user = new User();

    @Autowired
    private UserService userSerice;
    
    @GetMapping
    public String getRegister(){
        return "register";
    }
    
    @PostMapping("/handleform1")
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleForm1(@RequestBody Map<String, String> data){
        String username = data.get("username");
        String email = data.get("email");
        this.user.setUsername(username);
        this.user.setEmail(email);
        
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/handleform2")
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleForm2(@RequestParam String password){
        this.user.setPassword(password);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/checkOTP")
    @ResponseBody
    public ResponseEntity<Map<String, String>> checkOTP(HttpSession session, @RequestParam Integer otp){
        Map<String, String> response = new HashMap<>();
        
        if (otp == 123) {
            
            userSerice.saveUser(user);
            session.setAttribute("isUserLoggedIn", true);
            session.setAttribute("user", user);
            System.out.println(user);
            response.put("status", "success");
        } else {
            response.put("status", "failure");
        }
        return ResponseEntity.ok(response);
    }
}
