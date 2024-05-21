package com.example.storage.error;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        this("리소스를 찾을 수 없습니다");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
