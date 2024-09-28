package com.learnledger.controller;

import com.learnledger.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.learnledger.model.Card;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/card")
public class CardController {
    
    @Autowired
    private CardService cardService;
    
    
    @GetMapping
    public String adminView(Model model){
        model.addAttribute("card", new Card());
        return "admin";
    }
    
    @PostMapping("/saveCard")
    public String saveCard(@ModelAttribute("card") Card card){
        cardService.setCard(card);
        System.out.println(card);
        return "redirect:/";
    }
}
