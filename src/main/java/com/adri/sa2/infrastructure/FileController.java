package com.adri.sa2.infrastructure;

import com.adri.sa2.application.register.RegisterStorage;
import com.adri.sa2.application.storage.StorageFile;
import com.adri.sa2.exception.StorageException;
import com.adri.sa2.infrastructure.dto.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/file")
public class FileController {
    private final StorageFile storage;
    private final RegisterStorage register;

    @Autowired
    public FileController(StorageFile storage, RegisterStorage register){
        this.storage = storage;
        this.register = register;
    }

    @PostMapping("/upload/{extension}")
    public FileDTO storageFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable("extension")String ext,
            RedirectAttributes redirectAttributes
    ) {
        storage.store(file);
        FileDTO fileIn = new FileDTO(file.getOriginalFilename(), ext);
        FileDTO fileOut = new FileDTO(register.registerFile(fileIn));
        return fileOut;
    }


    @ExceptionHandler(StorageException.class)
    public ResponseEntity<StorageException> handleStorageException(StorageException exc) {
        ResponseEntity response = ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(exc.getMessage());
        return response;
    }
}
