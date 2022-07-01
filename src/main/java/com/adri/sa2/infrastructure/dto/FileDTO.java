package com.adri.sa2.infrastructure.dto;

import com.adri.sa2.domain.FileModel;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.bind.annotation.PathVariable;


import java.nio.file.Path;
import java.util.Date;

@Data
@ToString
public class FileDTO {
    private long id;
    private String extension;
    private String fileName;
    private Date uploadDate;
    private String path;

    //Output
    public FileDTO(FileModel file){
        setId(file.getId());
        setFileName(file.getFileName());
        setExtension(file.getExtension());
        setUploadDate(file.getUploadDate());
        setPath(file.getPath());
    }

    //Input
    public FileDTO(String fileName, String extension, String path){
        setFileName(fileName);
        setExtension(extension);
        setPath(path);
    }
}
