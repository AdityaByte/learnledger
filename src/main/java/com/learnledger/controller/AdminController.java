package com.learnledger.controller;

import com.learnledger.enums.DocumentType;
import com.learnledger.enums.UserType;
import com.learnledger.model.DataDocument;
import com.learnledger.model.Organization;
import com.learnledger.model.User;
import com.learnledger.service.DataService;
import com.learnledger.service.OrganizationService;
import com.learnledger.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController{
    
    @Autowired
    private UserService service;
    @Autowired
    private OrganizationService orgService;
    @Autowired
    private DataService dataService;
    
    
    @GetMapping
    public Object showAdminPage(HttpServletResponse response , HttpSession session){
        
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if(isAdmin == null){
            isAdmin = null;
        }
        
        if(isAdmin){
            Long usersNo = service.countByUserType(UserType.USER);
            Long organizationsNo = orgService.countByUserType(UserType.ORGANIZATION);
            Long notes = dataService.countByTypeofDocument(DocumentType.NOTES);
            Long projects = dataService.countByTypeofDocument(DocumentType.PROJECTS);
            Long pdfs = dataService.countByTypeofDocument(DocumentType.PDFS);
            Long docs = dataService.countByTypeofDocument(DocumentType.DOCS);
            
            List<User> users = (List<User>) service.findAllUserByUserType(UserType.USER);
            List<Organization> orgs = (List<Organization>) orgService.findAllOrganizationByUserType(UserType.ORGANIZATION);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("usersNo" , usersNo);
            modelAndView.addObject("organizationsNo", organizationsNo);
            modelAndView.addObject("notes" , notes);
            modelAndView.addObject("projects" , projects);
            modelAndView.addObject("pdfs" , pdfs);
            modelAndView.addObject("docs" , docs);
            modelAndView.addObject("users" , users);
            modelAndView.addObject("orgs" , orgs);            
            
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
    
    @PostMapping("/document")
    @ResponseBody
    public ResponseEntity<Map<String , String>> handleDocument(
            @ModelAttribute("dataDocument") DataDocument dataDocument,
            @RequestPart("docimg") MultipartFile docimg,
            @RequestPart("docfile") MultipartFile docfile
    ) throws IOException{
        if(dataDocument != null){
            System.out.println(dataDocument);
            dataService.saveDocument(dataDocument, docimg, docfile);
            return ResponseEntity.ok().body(Map.of("status" , "success"));
        }
        else{
            return ResponseEntity.ok().body(Map.of("status" , "failure"));
        }
    }
    
}