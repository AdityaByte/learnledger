package com.learnledger.model;

import com.learnledger.enums.UserType;
import com.learnledger.enums.TypeofNotes;
import com.learnledger.enums.TargetAudience;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Organization {
    @Id
    private String organizationId;
    private String organizationName;
    private String personName;
    private String email;
    private String phoneNumber;
    private UserType userType;
    private String websiteURL;
    private TypeofNotes typeofNotes;
    private TargetAudience targetAudience;
    private File businessRegistrationFile;
    private LocalDate creationDate = LocalDate.now();

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
    private Credentials credentials;

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public TypeofNotes getTypeofNotes() {
        return typeofNotes;
    }

    public void setTypeofNotes(TypeofNotes typeofNotes) {
        this.typeofNotes = typeofNotes;
    }

    public TargetAudience getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(TargetAudience targetAudience) {
        this.targetAudience = targetAudience;
    }

    public File getBusinessRegistrationFile() {
        return businessRegistrationFile;
    }

    public void setBusinessRegistrationFile(File businessRegistrationFile) {
        this.businessRegistrationFile = businessRegistrationFile;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Organization{" + "organizationId=" + organizationId + ", organizationName=" + organizationName + ", personName=" + personName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", userType=" + userType + ", websiteURL=" + websiteURL + ", typeofNotes=" + typeofNotes + ", targetAudience=" + targetAudience + ", businessRegistrationFile=" + businessRegistrationFile + ", creationDate=" + creationDate + '}';
    }

    
}
