package com.learnledger.model;

import com.learnledger.enums.DocumentType;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "files")
public class DataDocument {
    @Id
    private String id;
    private String title;
    private String subTitle;
    private String description;
    private DocumentType typeofDocument;
    private List<File> files;     

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DocumentType getTypeofDocument() {
        return typeofDocument;
    }

    public void setTypeofDocument(DocumentType typeofDocument) {
        this.typeofDocument = typeofDocument;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "DataDocument{" + "id=" + id + ", title=" + title + ", subTitle=" + subTitle + ", description=" + description + ", typeofDocument=" + typeofDocument + ", files=" + files + '}';
    }

    
    
}
