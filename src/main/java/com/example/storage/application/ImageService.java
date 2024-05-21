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
public class ImageService {
    private final FileManager fileManager;
    private final List<String> allowedExtensions = List.of(
            "jpg",
            "png",
            "jpeg",
            "gif",
            "svg"
    );

    public byte[] getByName(String fileName) {
        return fileManager.getByName(fileName, FileTypeEnum.IMAGE);
    }

    public FileUploadResponse save(MultipartFile[] files) {
        checkFileType(files);
        List<String> fileNames = fileManager.upload(files, FileTypeEnum.IMAGE);
        return new FileUploadResponse(fileNames);
    }

    public void delete(String fileName) {
        fileManager.delete(fileName, FileTypeEnum.IMAGE);
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
