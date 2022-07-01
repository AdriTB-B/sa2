package com.adri.sa2.application.storage;

import com.adri.sa2.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class StorageFileUseCase implements StorageFile {
    private final Path rootPath;

    @Autowired
    public StorageFileUseCase(StorageConfigurationProperties properties){
        this.rootPath = Path.of(properties.getPath());
    }

    @Override
    public void init() {

    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("El archivo: " + file.getOriginalFilename() + " está vacío");
            }
            System.out.println("Name: " + file.getName());
            System.out.println("Path: " + this.rootPath.resolve(file.getOriginalFilename()));
            System.out.println("Content: " + file.getBytes());
            Files.copy(file.getInputStream(), this.rootPath.resolve(file.getOriginalFilename()));
        } catch (IOException | RuntimeException ex) {
            throw new StorageException("Error en la subida del archivo: " + file.getOriginalFilename());
        }
    }
}
