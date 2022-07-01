package com.adri.sa2.application.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageConfigurationProperties {
    private String path = ".\\files";

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
