package com.adri.sa2.application.storage;

import com.adri.sa2.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class StorageFileUseCase implements StorageFile {
    private final StorageConfigurationProperties properties;

    @Autowired
    public StorageFileUseCase(StorageConfigurationProperties properties){
        this.properties = properties;
    }

    @Override
    public Path store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("El archivo: " + file.getOriginalFilename() + " está vacío");
            }
            Path path = Path.of(properties.getPath()).resolve(file.getOriginalFilename());
            System.out.println("Name: " + file.getOriginalFilename());
            System.out.println("Path: " + path);
            System.out.println("Content: " + file.getBytes());
            Files.copy(file.getInputStream(), path);
            return path;
        } catch (IOException | RuntimeException ex) {
            throw new StorageException("Error en la subida del archivo: " + file.getOriginalFilename());
        }
    }

    @Override
    public Resource download(Path filePath) {
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageException("No se puede leer el archivo: " + filePath);

            }
        } catch (MalformedURLException e) {
            throw new StorageException("Error en la url del archivo: " + filePath);
        }
    }
}
