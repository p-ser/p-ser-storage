package com.pser.storage.error;

public class FileUploadException extends RuntimeException {
    public FileUploadException() {
        this("파일 업로드 실패");
    }

    public FileUploadException(String message) {
        super(message);
    }
}
