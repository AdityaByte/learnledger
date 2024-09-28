package com.learnledger.controller;

// In the datacontroller we are implementing the logic of saving all the things Notes,Projects,Pdfs,and Docs

import com.learnledger.model.DataDocument;
import com.learnledger.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/document")
public class DataController {
    
    @Autowired
    private DataService service;
    
    @GetMapping
    public String showForm(Model model){
        model.addAttribute("DataDocument" , new DataDocument());
        return "document";
    }
    
    @PostMapping
    @ResponseBody
    public String saveDocument(@ModelAttribute("DataDocument") DataDocument dataDocument , @RequestParam("image") MultipartFile image , @RequestParam("file") MultipartFile file){
        try{
            service.saveDocument(dataDocument, image, file);
            return "Document is saved successfully";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return "Not saved getting some error";
    }
}
