package com.learnledger.service;

import com.learnledger.model.DataDocument;
import com.learnledger.model.File;
import com.learnledger.repository.DataRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DataService {
    
    @Autowired
    private DataRepository repository;

    public String convertBinaryToBase64(byte[] binaryData) {
        return Base64.getEncoder().encodeToString(binaryData);
    }
    
    public DataDocument saveDocument(DataDocument dataDocument, MultipartFile image, MultipartFile file) throws IOException {
        
        File img = new File();
        img.setFileName(image.getOriginalFilename());
        img.setFileType(image.getContentType());
        img.setBase64file(convertBinaryToBase64(image.getBytes()));
        
        File file1 = new File();
        file1.setFileName(file.getOriginalFilename());
        file1.setFileType(file.getContentType());
        file1.setBase64file(convertBinaryToBase64(file.getBytes()));
        
        List<File> list = new ArrayList<>();
        list.add(img);
        list.add(file1);
        
        dataDocument.setFiles(list);
        
        return repository.save(dataDocument);
    }
    
    public List<DataDocument> fetchAllDocuments() {
        return repository.findAll();
    }
    
    public List<DataDocument> findByFieldName(String typeofDocument) {
        return repository.findByTypeofDocument(typeofDocument);
    }
}