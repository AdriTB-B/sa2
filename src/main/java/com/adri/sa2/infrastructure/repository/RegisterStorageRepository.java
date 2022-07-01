package com.adri.sa2.infrastructure.repository;

import com.adri.sa2.domain.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.util.List;

@Repository
public interface RegisterStorageRepository extends JpaRepository<FileModel, Long> {
    List<FileModel> findByFileName(String fileName);
}
