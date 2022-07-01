package com.adri.sa2.application.register;

import com.adri.sa2.domain.FileModel;
import com.adri.sa2.infrastructure.dto.FileDTO;

import java.io.File;
import java.nio.file.Path;

public interface RegisterStorage {
    FileModel registerFile(FileDTO file);

    Path findByName(String fileName);

    Path findById(long fileId);
}
