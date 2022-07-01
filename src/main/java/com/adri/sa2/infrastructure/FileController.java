package com.adri.sa2.infrastructure;

import com.adri.sa2.application.register.RegisterStorage;
import com.adri.sa2.application.storage.StorageConfigurationProperties;
import com.adri.sa2.application.storage.StorageFile;
import com.adri.sa2.exception.RegisterStorageException;
import com.adri.sa2.exception.StorageException;
import com.adri.sa2.infrastructure.dto.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;

@RestController
@RequestMapping("/file")
public class FileController {
    private final StorageFile storageFile;
    private final RegisterStorage registerStorage;
    private final StorageConfigurationProperties properties;

    @Autowired
    public FileController(StorageFile storageFile, RegisterStorage registerStorage, StorageConfigurationProperties properties){
        this.storageFile = storageFile;
        this.registerStorage = registerStorage;
        this.properties = properties;
    }

    @PostMapping("/upload/{extension}")
    public FileDTO storageFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable("extension")String ext,
            RedirectAttributes redirectAttributes
    ) {
        String path = storageFile.store(file).toString();
        FileDTO fileIn = new FileDTO(file.getOriginalFilename(), ext, path);
        FileDTO fileOut = new FileDTO(registerStorage.registerFile(fileIn));
        return fileOut;
    }

    @GetMapping("/download/file-name/{fileName}")
    @ResponseBody
    public Resource downloadFileByName(@PathVariable("fileName")String fileName){
        Path filePath = registerStorage.findByName(fileName);
        return storageFile.download(filePath);
    }
    @GetMapping("/download/file-id/{fileId}")
    public Resource downloadFileById(@PathVariable("fileId")long fileId){
        Path filePath = registerStorage.findById(fileId);
        return storageFile.download(filePath);
    }

    @GetMapping("/set-path")
    public String setPath(@RequestParam(name = "path", required = true)String path){
        properties.setPath(path);
        return "La ruta de guardado se ha cambiado a " + properties.getPath();
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<StorageException> handleStorageException(StorageException exc) {
        ResponseEntity response = ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(exc.getMessage());
        return response;
    }
    @ExceptionHandler(RegisterStorageException.class)
    public ResponseEntity<RegisterStorageException> handleRegisterException(RegisterStorageException exc) {
        ResponseEntity response = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exc.getMessage());
        return response;
    }
}
