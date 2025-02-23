package com.project1.call_management_app.controller;

import com.project1.call_management_app.service.TranscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/transcription")
@Tag(name = "Transcription API", description = "API for uploading and transcribing audio files")
public class TranscriptionController {

    private final TranscriptionService transcriptionService;

    @Autowired
    public TranscriptionController(TranscriptionService transcriptionService) {
        this.transcriptionService = transcriptionService;
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @Operation(
            summary = "Upload an audio file for transcription",
            description = "Uploads an audio file and processes its transcription.",
            requestBody = @RequestBody(
                    required = true,
                    description = "Audio file for transcription",
                    content = @Content(
                            mediaType = "multipart/form-data",
                            schema = @Schema(type = "string", format = "binary")
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "File uploaded and processed successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: File is empty");
            }

            String transcriptionResult = transcriptionService.processFile(file);
            return ResponseEntity.ok(transcriptionResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
