package com.example.springOne.star.image;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class Image {
    @Id
    private String id;
    private String name;
    private String contentType;
    private byte[] data;

    // Constructors
    public Image() {
    }

    public Image(String name, String contentType, byte[] data) {
        this.name = name;
        this.contentType = contentType;
        this.data = data;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}

