package com.adri.sa2;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class FileModel {
    private MultipartFile file;
    private String extension;
    private String fileName;
    private Date uploadDate;
}
