package com.example.storage.infra;

import lombok.Getter;

@Getter
public enum FileTypeEnum {
    IMAGE("image"),
    PDF("pdf");

    private final String value;

    FileTypeEnum(String value) {
        this.value = value;
    }
}
