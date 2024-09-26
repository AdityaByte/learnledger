package com.learnledger.controller;

import com.learnledger.enums.TargetAudience;
import com.learnledger.enums.TypeofNotes;
import com.learnledger.enums.UserType;
import com.learnledger.model.Credentials;
import com.learnledger.model.Organization;
import com.learnledger.utils.CredentailsGenerator;
import com.learnledger.utils.OTPGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("org")
public class OrganizationController {
    
    private List<Organization> organizationList = new ArrayList<>();
    private CredentailsGenerator credentailsGenerator = new CredentailsGenerator();
    private OTPGenerator otpGenerator = new OTPGenerator();
    
    private Organization org = new Organization();
    private Credentials cred = new Credentials();
    
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
            @RequestPart("licenseFile") MultipartFile licenseFile,
            @RequestParam("agreeInput") String agreeInput
    ) {
        if("checked".equals(agreeInput)){
            String orgId = credentailsGenerator.generateIdForOrganization(orgName);
            org.setOrganizationId(orgId);
            org.setOrganizationName(orgName);
            org.setPersonName(personName);
            org.setPhoneNumber(phoneNo);
            org.setEmail(email);
            org.setUserType(UserType.ORGANIZATION);
            org.setWebsiteURL(websiteURL);
            org.setTargetAudience(TargetAudience.valueOf(targetAudience));
            org.setTypeofNotes(TypeofNotes.valueOf(typeofNotes));
            System.out.println(org);
            otpGenerator.generateOtp();
            String otp = otpGenerator.getOtp();
            System.out.println("Your otp is -> " + otp);
            
        }
        HashMap<String , String> response = new HashMap<>();
        System.out.println("working...");
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/validateOTP")
    @ResponseBody
    public ResponseEntity<Map<String , String>> validateOTP(
            @RequestBody Map<String , String> requestBody
    ){
        String otp = requestBody.get("otp");
        if(otpGenerator.getOtp().equals(otp)){
            Credentials cred = new Credentials();
            String email = credentailsGenerator.generateEmailForOrganization(org.getOrganizationId());
            String password = credentailsGenerator.generatePasswordForOrganization(org.getOrganizationId());
            cred.setEmail(email);
            cred.setOrganizationId(org.getOrganizationId());
            cred.setHashedPassword(password);
            System.out.println(cred);
            return ResponseEntity.ok().body(Map.of("status" , "success"));
        }
        else{
            return ResponseEntity.ok().body(Map.of("status" , "failure"));
        }
    }
}