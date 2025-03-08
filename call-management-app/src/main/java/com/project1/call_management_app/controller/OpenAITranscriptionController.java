package com.project1.call_management_app.controller;

import com.theokanning.openai.audio.CreateTranscriptionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api/openaitranscription")
public class OpenAITranscriptionController {

    @Value("${openai.api.key}")
    private String apiKey;

    @GetMapping("/audio")
    public String transcribeAudio(@RequestParam String filePath) {
        try {
            // Initialize OpenAI Service
            OpenAiService openAiService = new OpenAiService(apiKey);

            // Validate File
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                return "Error: File not found at path: " + filePath;
            }

            // Create transcription request
            CreateTranscriptionRequest request = CreateTranscriptionRequest.builder()
                    .model("whisper-1")
                    .build();

            // Get Transcription
            String transcription = openAiService.createTranscription(request, file).getText();
            return transcription;

        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing transcription: " + e.getMessage();
        }
    }
}
