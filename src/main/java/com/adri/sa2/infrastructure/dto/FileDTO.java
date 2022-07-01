package com.adri.sa2.infrastructure.dto;

import com.adri.sa2.domain.FileModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;

@Data
@ToString
public class FileDTO {
    private long id;
    private String extension;
    private String fileName;
    private Date uploadDate;

    //Output
    public FileDTO(FileModel file){
        setId(file.getId());
        setFileName(file.getFileName());
        setExtension(file.getExtension());
        setUploadDate(file.getUploadDate());
    }

    //Input
    public FileDTO(String fileName, String extension){
        setFileName(fileName);
        setExtension(extension);
    }
}
