package com.adri.sa2.application.register;

import com.adri.sa2.domain.FileModel;
import com.adri.sa2.infrastructure.dto.FileDTO;
import com.adri.sa2.infrastructure.repository.RegisterStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterStorageUseCase implements RegisterStorage{
    @Autowired
    RegisterStorageRepository repository;

    @Override
    public FileModel registerFile(FileDTO file) {
        return repository.save(new FileModel(file));
    }
}
