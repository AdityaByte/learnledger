package com.learnledger.controller;

import com.learnledger.model.DataDocument;
import com.learnledger.service.CardService;
import com.learnledger.service.DataService;
import java.util.ArrayList;
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
    
    @Autowired
    private CardService cardService;
    
    @GetMapping
    public String getHome(Model model){
        
        
        if(cardService.checkMongoDBConnection()){

            try{
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

            }
            catch(Exception e){
                e.printStackTrace();
                model.addAttribute("error", "Getting some error while fetching data from the database check out the database and logs for more info");
                model.addAllAttributes(Map.of(
                        "notes", new ArrayList<>(),
                        "projects", new ArrayList<>(),
                        "pdfs", new ArrayList<>(),
                        "docs", new ArrayList<>()
                    )
                );    
            }
        }
        else{
            model.addAllAttributes(
                Map.of(
                    "error", "Getting some error while fetching data from the database check out the database and logs for more info",
                    "notes", new ArrayList<>(),
                    "projects", new ArrayList<>(),
                    "pdfs", new ArrayList<>(),
                    "docs", new ArrayList<>()                    
                )
            );
        }
        // Instead to doing the upper two block code we can just pass the HashMap to the model attribute
        
        return "home";
    }

    
}
