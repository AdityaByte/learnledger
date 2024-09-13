package com.learnledger.controller;

import com.learnledger.model.User;
import com.learnledger.service.CardService;
import com.learnledger.service.UserService;
import com.mongodb.MongoException;
import com.mongodb.MongoTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController{
    
    @Autowired
    private CardService cardService;
    
    
    public String getRandomHeading(){
        List<String> headerItems = new ArrayList<>();
        // Add items to the list
        headerItems.add("Where Retailers Thrive and Shoppers Discover");
        headerItems.add("Empowering Retailers, Inspiring Shoppers");
        headerItems.add("Where Retailers Excel and Shoppers Explore");
        headerItems.add("Your Marketplace for Retail Success and Shopper Delight");
        headerItems.add("Connecting Retailers with Shoppers: Thrive and Discover");
        headerItems.add("Where Retail Opportunities Meet Shopper Discoveries");
        
        Random random = new Random();
        
        int randomIndex = random.nextInt(headerItems.size());
        String randomItem = headerItems.get(randomIndex);
        
        return randomItem;
    }
    
    @GetMapping
    public String showIndex(Model model, HttpSession session) {
        Boolean isUserLoggedIn = (Boolean) session.getAttribute("isUserLoggedIn");
        if (isUserLoggedIn == null) {
            isUserLoggedIn = false;
        }

        String randomItem = getRandomHeading();
        model.addAttribute("randomH1", randomItem);
        model.addAttribute("isUserLoggedIn", isUserLoggedIn);
        
        if(cardService.checkMongoDBConnection()){
            try {
                model.addAttribute("cards", cardService.getCards());
            } catch (Exception e) {
                model.addAttribute("error", "Unable to load cards at the moment.");
                model.addAttribute("cards", new ArrayList<>());  // Avoid null, use an empty list
            }
        }
        else{
            model.addAttribute("error", "Database connection issue.");
            model.addAttribute("cards", new ArrayList<>());
        }
        System.out.println("Working... Home handler method");
        return "index";
    }
    
    
    @GetMapping("/logout")
    public String logoutHandler(HttpSession session){
        session.setAttribute("isUserLoggedIn", false);
        return "redirect:/";
    }
    
}