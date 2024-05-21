package com.example.storage.api;

import com.example.storage.application.ImageService;
import com.example.storage.dto.ApiResponse;
import com.example.storage.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image/files")
@RequiredArgsConstructor
public class ImageApi {
    private final ImageService imageService;

    @GetMapping(value = "/{imageName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE,
            MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public byte[] getByName(@PathVariable("imageName") String imageName) {
        return imageService.getByName(imageName);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<FileUploadResponse>> save(@RequestPart MultipartFile[] files) {
        FileUploadResponse result = imageService.save(files);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @DeleteMapping("/{imageName}")
    public ResponseEntity<Void> delete(@PathVariable("imageName") String imageName) {
        imageService.delete(imageName);
        return ResponseEntity.ok().build();
    }
}