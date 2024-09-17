package com.learnledger.controller;

import com.learnledger.model.User;
import com.learnledger.service.EmailService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/register")
public class RegisterFormController {
    
    private final User user = new User();

    @Autowired
    private UserService userService; // Corrected typo in variable name
    
    @Autowired
    private EmailService emailService;
    
    @GetMapping
    public String getRegister(){
        return "register";
    }
    
    @PostMapping("/handleform1")
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleForm1(@RequestBody Map<String, String> data){
        String username = data.get("username");
        String email = data.get("email");
        
        if(userService.checkEmailExists(email)){
            System.out.println("email exists already");
            return ResponseEntity.badRequest().body(Map.of("status" ,"emailExists"));
        }
        else{
            System.out.println("no email is new");
            this.user.setUsername(username);
            this.user.setEmail(email);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            return ResponseEntity.ok(response);
        }
    }
    
    @PostMapping("/handleform2")
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleForm2(@RequestParam String password, HttpSession session){
        this.user.setPassword(password);
        
        try {
            String email = this.user.getEmail();
            if (email != null && !email.isEmpty()) {
                System.out.println(email);
                emailService.generateOTP();
                String otp = emailService.getOTP();
                System.out.println(otp);
                //emailService.sendMail(email, "One Time Password - Learnledger", "Hello, This is the OTP for your Login is: " + otp);
                session.setAttribute("sessionOtp", otp);
                
                Map<String, String> response = new HashMap<>();
                response.put("status", "success");
                return ResponseEntity.ok(response);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("status", "error");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("status", "error");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @PostMapping("/checkOTP")
    @ResponseBody
    public ResponseEntity<Map<String, String>> checkOTP(HttpSession session, @RequestParam String otp){
        Map<String , String> response = new HashMap<>();
        
        if (otp.equals(session.getAttribute("sessionOtp"))) {
            try{
                this.user.setUserType("user");
                userService.saveUser(user);
                session.setAttribute("isUserLoggedIn", true);
                session.setAttribute("user", user);
                response.put("status", "success");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        } else {
            response.put("status", "otpNotMatched");
        }
        return ResponseEntity.ok(response);
    }
}
