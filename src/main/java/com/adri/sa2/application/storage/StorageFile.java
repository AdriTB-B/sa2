package com.adri.sa2.application.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageFile {
    void init();
    void store(MultipartFile file);
}
