package com.learnledger.controller;

import com.learnledger.enums.DocumentType;
import com.learnledger.model.DataDocument;
import com.learnledger.service.CardService;
import com.learnledger.service.DataService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    public HomeController() {
        System.out.println("i am the controller being called by someone @");
    }
    

    @Autowired
    private DataService service;
    
    @Autowired
    private CardService cardService;
    
    @GetMapping
    public String getHome(Model model , HttpSession session){
        
        Boolean isUserLoggedIn = (Boolean) session.getAttribute("isUserLoggedIn");
        if(isUserLoggedIn == null){
            isUserLoggedIn = false;
        }
        model.addAttribute("isUserLoggedIn", isUserLoggedIn);
        
        if(cardService.checkMongoDBConnection()){

            try{
                Map<String, List<DataDocument>> documents = 
                    Stream.of(DocumentType.NOTES, DocumentType.PROJECTS, DocumentType.PDFS)
                    .collect(Collectors.toMap(
                            field -> field.name(),
                            field -> service.findByFieldName(field)
                                .stream()
                                .limit(4)
                                .collect(Collectors.toList())
                    ));

                List<DataDocument> notes = documents.get(DocumentType.NOTES.name());
                List<DataDocument> projects = documents.get(DocumentType.PROJECTS.name());
                List<DataDocument> pdfs = documents.get(DocumentType.PDFS.name());

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

    @GetMapping("/content/{contentNo}")
    public String showContent(@PathVariable("contentNo") int contentNo , Model model){
        List<DataDocument> notes = service.findByFieldName(DocumentType.NOTES);
        List<DataDocument> projects = service.findByFieldName(DocumentType.PROJECTS);
        List<DataDocument> pdfs = service.findByFieldName(DocumentType.PDFS);
        List<DataDocument> docs = service.findByFieldName(DocumentType.DOCS);

        switch(contentNo){
            case 1:
                model.addAttribute("data" , notes);
                break;
            case 2:
                model.addAttribute("data" , projects);
                break;
            case 3:
                model.addAttribute("data" , pdfs);
                break;
            case 4:
                model.addAttribute("data" , docs);
                break;
            default:
                System.out.println("Invalid url routing");
                break;
        }
        
        return "content";
    }
}
