package com.example.springOne.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document (collection = "projects")
public class Project {

    @Id
    private String id;
    private String starId;
    private String category;
    private String title;
    private String description;
    private double fundingAmount;
    private double returnPercentage;
    private String status;

    // Default constructor
    public Project() {
    }

    // Parameterized constructor
    public Project(String starId, String category, String title, String description, double fundingAmount, double returnPercentage, String status) {
        this.starId = starId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.fundingAmount = fundingAmount;
        this.returnPercentage = returnPercentage;
        this.status = status;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStarId() {
        return starId;
    }

    public void setStarId(String starId) {
        this.starId = starId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFundingAmount() {
        return fundingAmount;
    }

    public void setFundingAmount(double fundingAmount) {
        this.fundingAmount = fundingAmount;
    }

    public double getReturnPercentage() {
        return returnPercentage;
    }

    public void setReturnPercentage(double returnPercentage) {
        this.returnPercentage = returnPercentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

