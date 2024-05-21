package com.example.storage.api;

import com.example.storage.application.PdfService;
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
@RequestMapping("/pdf/files")
@RequiredArgsConstructor
public class PdfApi {
    private final PdfService pdfService;

    @GetMapping(value = "/{fileName}", produces = {MediaType.APPLICATION_PDF_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public byte[] getByName(@PathVariable("fileName") String fileName) {
        return pdfService.getByName(fileName);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<FileUploadResponse>> save(@RequestPart MultipartFile[] files) {
        FileUploadResponse result = pdfService.save(files);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity<Void> delete(@PathVariable("fileName") String fileName) {
        pdfService.delete(fileName);
        return ResponseEntity.ok().build();
    }
}