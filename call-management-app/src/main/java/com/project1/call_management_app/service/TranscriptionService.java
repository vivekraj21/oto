package com.project1.call_management_app.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface TranscriptionService {
    String processFile(MultipartFile file) throws IOException;

}
