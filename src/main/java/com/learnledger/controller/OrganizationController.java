package com.learnledger.controller;

import com.learnledger.enums.TargetAudience;
import com.learnledger.enums.TypeofNotes;
import com.learnledger.enums.UserType;
import com.learnledger.model.Credentials;
import com.learnledger.model.File;
import com.learnledger.model.Organization;
import com.learnledger.service.OrganizationService;
import com.learnledger.utils.Base64Converter;
import com.learnledger.utils.OTPGenerator;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("org")
public class OrganizationController {

    @Autowired
    private OrganizationService service;
    
    private OTPGenerator otpGenerator = new OTPGenerator();
    private Organization org;
    
    @GetMapping("/form")
    public String showOrgForm() {
        return "organization";
    }

    @PostMapping("/form")
    @ResponseBody
    public ResponseEntity<Map<String, String>> processForm(
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
    ) throws IOException {

        if (!"checked".equals(agreeInput)) {
            return ResponseEntity.badRequest().body(Map.of("status", "error", "message", "You must agree to the terms."));
        }

        org = new Organization();

        File license = new File();
        license.setFileName(licenseFile.getOriginalFilename());
        license.setFileType(licenseFile.getContentType());
        license.setBase64file(Base64Converter.convertBinaryToBase64(licenseFile.getBytes()));

        org.setOrganizationName(orgName);
        org.setPersonName(personName);
        org.setPhoneNumber(phoneNo);
        org.setEmail(email);
        org.setUserType(UserType.ORGANIZATION);
        org.setWebsiteURL(websiteURL);
        org.setTargetAudience(TargetAudience.valueOf(targetAudience));
        org.setTypeofNotes(TypeofNotes.valueOf(typeofNotes));
        org.setBusinessRegistrationFile(license);

        otpGenerator.generateOtp();
        String otp = otpGenerator.getOtp();
        System.out.println(otp);
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validateOTP")
    @ResponseBody
    public ResponseEntity<Map<String, String>> validateOTP(@RequestBody Map<String, String> requestBody , HttpSession session) {
        HashMap<String, String> response = new HashMap<>();

        String otp = requestBody.get("otp");

        if (otp != null && otp.equals(otpGenerator.getOtp())) {
            Organization org = this.org;
            if (org != null) {
                Credentials credentials = service.saveOrganization(org);
                response.put("email", credentials.getEmail());
                response.put("password", credentials.getPassword());
                session.setAttribute("isUserLoggedIn", true);
                session.setAttribute("currentUser", org);
                response.put("status", "success");
            } else {
                response.put("status", "error");
                response.put("message", "Organization not found.");
            }
        } else {
            response.put("status", "failure");
            response.put("message", "Invalid OTP.");
        }
        return ResponseEntity.ok(response);
    }
}
