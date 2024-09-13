package com.learnledger.service;

import com.learnledger.model.Card;
import com.learnledger.repository.CardRepository;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import java.util.List;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CardService{
    
    @Autowired
    private CardRepository repository;
    
    
    //Checking mongodb connection
    
    public boolean checkMongoDBConnection(){
        try(MongoClient client = MongoClients.create(MongoClientSettings.builder().applyConnectionString(new ConnectionString("mongodb://localhost:27017")).build())){
            client.getDatabase("adityadatabase").runCommand(new Document("ping" , 1));
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    
    @Cacheable("card")
    public List<Card> getCards(){
        return repository.findAll();
    }
    
    public Card setCard(Card card){
        return repository.save(card);
    }
    
}

