package com.learnledger.controller;

import com.learnledger.model.Notes;
import com.learnledger.service.NotesService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    
    @Autowired
    private NotesService service;
    
    @GetMapping
    public String getHome(Model model){
        
        List<Notes> notesList = service.getAllNotes();
        
        List<Notes> limitedList = notesList.stream().limit(4).collect(Collectors.toList());
        
        model.addAttribute("notes", limitedList);
        
        return "home";
    }

    
}
