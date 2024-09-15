package com.learnledger.controller;

import com.learnledger.model.DataDocument;
import com.learnledger.service.DataService;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private DataService service;
    
    @GetMapping
    public String getHome(Model model){
        
        Map<String , List<DataDocument>>  documents = 
                Stream.of("notes" , "projects" , "pdfs")
                .collect(Collectors.toMap(
                        field -> field,
                        field -> service.findByFieldName(field)
                            .stream()
                            .limit(4)
                            .collect(Collectors.toList())
                ));
        
        List<DataDocument> notes = documents.get("notes");
        List<DataDocument> projects = documents.get("projects");
        List<DataDocument> pdfs = documents.get("pdfs");

        
        model.addAttribute("notes" , notes);
        model.addAttribute("projects" , projects);
        model.addAttribute("pdfs", pdfs);
        
        // Instead to doing the upper two block code we can just pass the HashMap to the model attribute
        
        return "home";
    }

    
}
