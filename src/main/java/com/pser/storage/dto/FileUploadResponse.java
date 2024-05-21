package com.pser.storage.dto;

import java.util.List;
import lombok.Data;

@Data
public class FileUploadResponse {
    private List<String> fileNames;

    public FileUploadResponse(List<String> fileNames) {
        this.fileNames = fileNames;
    }
}
