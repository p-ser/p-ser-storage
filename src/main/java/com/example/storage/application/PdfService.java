package com.example.storage.application;

import com.example.storage.dto.FileUploadResponse;
import com.example.storage.error.FileExtensionException;
import com.example.storage.infra.FileManager;
import com.example.storage.infra.FileTypeEnum;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class PdfService {
    private final FileManager fileManager;
    private final List<String> allowedExtensions = List.of(
            "pdf"
    );

    public byte[] getByName(String fileName) {
        return fileManager.getByName(fileName, FileTypeEnum.PDF);
    }

    public FileUploadResponse save(MultipartFile[] files) {
        checkFileType(files);
        List<String> fileNames = fileManager.upload(files, FileTypeEnum.PDF);
        return new FileUploadResponse(fileNames);
    }

    public void delete(String fileName) {
        fileManager.delete(fileName, FileTypeEnum.PDF);
    }

    private void checkFileType(MultipartFile[] files) {
        Arrays.stream(files).forEach(file -> {
            String originalFilename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFilename);
            if (!allowedExtensions.contains(extension)) {
                throw new FileExtensionException();
            }
        });
    }
}
