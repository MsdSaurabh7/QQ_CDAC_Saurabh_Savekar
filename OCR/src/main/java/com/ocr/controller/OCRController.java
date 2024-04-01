package com.ocr.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OCRController {

    @PostMapping("/api/performOCR")
    public String performOCR(@RequestParam("file") MultipartFile file) {
        // Save uploaded file to a temporary location
        // Call OCR algorithm on the uploaded file
        // Return extracted text
        return "Extracted text from OCR";
    }
}

