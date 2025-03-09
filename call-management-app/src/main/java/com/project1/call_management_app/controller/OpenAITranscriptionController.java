package com.project1.call_management_app.controller;

import com.project1.call_management_app.service.TranscriptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openaitranscription")
public class OpenAITranscriptionController {

    private final TranscriptionService transcriptionService;

    public OpenAITranscriptionController(TranscriptionService transcriptionService) {
        this.transcriptionService = transcriptionService;
    }

    @GetMapping("/audio")
    public String transcribeAudio(@RequestParam String filePath) {
        return transcriptionService.transcribeAudio(filePath);
    }
}
