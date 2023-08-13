package com.fileuploading.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String fileName;

    public FileUpload() {
    }

    public FileUpload(int id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileUpload{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
