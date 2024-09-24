package com.learnledger.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("org")
public class OrganizationController {
    
    @GetMapping("/form")
    public String showOrgForm(){
        return "organization";
    }
    
    @PostMapping("/form")
    @ResponseBody
    public ResponseEntity<Map<String , String>> processForm(
            @RequestParam("orgName") String orgName,
            @RequestParam("personName") String personName,
            @RequestParam("orgEmail") String email,
            @RequestParam("orgPhoneNo") String phoneNo,
            @RequestParam("websiteURL") String websiteURL,
            @RequestParam("typeofNotes") String typeofNotes,
            @RequestParam("description") String description,
            @RequestParam("targetAudience") String targetAudience,
            @RequestPart("workSampleFile") MultipartFile workSampleFile,
            @RequestPart("licenseFile") MultipartFile licenseFile
    ) {
        HashMap<String , String> response = new HashMap<>();
        System.out.println("working...");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
}