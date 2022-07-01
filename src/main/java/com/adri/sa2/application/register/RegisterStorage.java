package com.adri.sa2.application.register;

import com.adri.sa2.domain.FileModel;
import com.adri.sa2.infrastructure.dto.FileDTO;

import java.io.File;

public interface RegisterStorage {
    FileModel registerFile(FileDTO file);
}
