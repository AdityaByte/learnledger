package com.learnledger.controller;

import com.learnledger.model.Notes;
import com.learnledger.service.NotesService;
import java.io.IOException;
import java.util.List;
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
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NotesService service;
    
    @GetMapping
    public String uploadNotes(Model model){
        model.addAttribute("notes", new Notes());
        return "notes";
    }
    
    
    
    @PostMapping(value = "/savenotes" , consumes = "multipart/form-data", produces = "text/html")
    @ResponseBody
    public String saveNotes(@ModelAttribute Notes notes , @RequestParam MultipartFile file) throws IOException{
        service.saveNotes(notes, file);
        return "<h1>Success</h1>";
    }
    
    @GetMapping("/shownotes")
    public String showNotes(Model model){
        
        List<Notes> notes = service.getAllNotes();
        
        model.addAttribute("notes", notes);
        
        return "showNotes";
    }
    
}
