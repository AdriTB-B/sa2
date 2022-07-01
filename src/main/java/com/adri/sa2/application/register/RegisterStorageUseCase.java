package com.adri.sa2.application.register;

import com.adri.sa2.domain.FileModel;
import com.adri.sa2.exception.RegisterStorageException;
import com.adri.sa2.infrastructure.dto.FileDTO;
import com.adri.sa2.infrastructure.repository.RegisterStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class RegisterStorageUseCase implements RegisterStorage{
    @Autowired
    RegisterStorageRepository repository;

    @Override
    public FileModel registerFile(FileDTO file) {
        return repository.save(new FileModel(file));
    }

    @Override
    public Path findByName(String fileName) {
        return Path.of(repository.findByFileName(fileName).get(0).getPath());
    }

    @Override
    public Path findById(long fileId) {
        return Path.of(
                repository
                        .findById(fileId)
                        .orElseThrow(() -> {
                            throw new RegisterStorageException("No se encuentra registrado ningún archivo con id " + fileId);
                        })
                        .getPath()
        );
    }
}
