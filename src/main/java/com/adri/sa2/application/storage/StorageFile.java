package com.adri.sa2.application.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageFile {
    void init();
    Path store(MultipartFile file);

    Resource download(Path fileName);
}
