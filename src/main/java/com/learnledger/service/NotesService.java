package com.learnledger.service;

import com.learnledger.model.Image;
import com.learnledger.model.Notes;
import com.learnledger.repository.NotesRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NotesService {
    
    @Autowired
    private NotesRepository repository;
    
    public String convertBinaryToBase64(byte[] binaryData){
        return Base64.getEncoder().encodeToString(binaryData);
    }
    
    public Notes saveNotes(Notes notes , MultipartFile file) throws IOException{
        
        Image image = new Image();
        image.setImageName(file.getOriginalFilename());
        image.setImageType(file.getContentType());
        image.setBase64Image(convertBinaryToBase64(file.getBytes()));
        notes.setImage(image);
        
        return repository.save(notes);
    }
    
    public List<Notes> getAllNotes(){
        return repository.findAll();
    }
    
    public List<Notes> searchByQuery(String query){
        return repository.findByTitleContainingIgnoreCase(query);
    }
    
    
    
}
