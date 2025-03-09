package com.project1.call_management_app.service.impl;

import com.project1.call_management_app.service.TranscriptionService;
import com.theokanning.openai.audio.CreateTranscriptionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class TranscriptionServiceImpl implements TranscriptionService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Override
    public String transcribeAudio(String filePath) {
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
            return openAiService.createTranscription(request, file).getText();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error processing transcription: " + e.getMessage();
        }
    }
}
