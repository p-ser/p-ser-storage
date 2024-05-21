package com.example.storage.error;

public class FileExtensionException extends RuntimeException {
    public FileExtensionException() {
        this("업로드할 수 없는 파일 확장자");
    }

    public FileExtensionException(String message) {
        super(message);
    }
}
