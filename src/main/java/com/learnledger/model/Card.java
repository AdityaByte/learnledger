package com.learnledger.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "card")
public class Card{
    
    @Id
    private String id;
    private String logoText;
    private String title;
    private String subTitle;
    private String description;
    private String subDescription;

    public String getLogoText() {
        return logoText;
    }

    public void setLogoText(String logoText) {
        this.logoText = logoText;
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

    public String getSubDescription() {
        return subDescription;
    }

    public void setSubDescription(String subDescription) {
        this.subDescription = subDescription;
    }

    @Override
    public String toString() {
        return "Card{" + "logoText=" + logoText + ", title=" + title + ", subTitle=" + subTitle + ", description=" + description + ", subDescription=" + subDescription + '}';
    }
    
    
    
}