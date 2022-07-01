package com.adri.sa2.domain;

import com.adri.sa2.infrastructure.dto.FileDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.nio.file.Path;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "files")
public class FileModel {
    @Id
    @GeneratedValue
    private long id;
    //private MultipartFile file;
    private String extension;
    private String fileName;
    private Date uploadDate;
    private String path;

    public FileModel(FileDTO file){
        setFileName(file.getFileName());
        setExtension(file.getExtension());
        setUploadDate(new Date());
        setPath(file.getPath());
    }

}
