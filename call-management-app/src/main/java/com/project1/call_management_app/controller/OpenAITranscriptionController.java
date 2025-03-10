package com.project1.call_management_app.controller;
import com.project1.call_management_app.dto.CallRecordDTO;
import com.project1.call_management_app.model.CallRecord;
import com.project1.call_management_app.service.TranscriptionService;
import com.project1.call_management_app.service.CallRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openaitranscription")
public class OpenAITranscriptionController {

    private final TranscriptionService transcriptionService;
    private final CallRecordService callRecordService;

    public OpenAITranscriptionController(TranscriptionService transcriptionService, CallRecordService callRecordService) {
        this.transcriptionService = transcriptionService;
        this.callRecordService = callRecordService;
    }

    @PostMapping("/audio")
    public ResponseEntity<?> transcribeAudio(@RequestBody CallRecordDTO callRecordDTO) {
        String filePath = callRecordDTO.getIncomingFilePath();

        try {
            // Attempt transcription
            String transcription = transcriptionService.transcribeAudio(filePath);

            // Check if the response contains an error message
            if (transcription == null || transcription.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Transcription failed: Received empty or null transcription.");
            }

            if (transcription.toLowerCase().contains("error")) {  // General error handling
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Transcription failed: " + transcription);
            }

            // Set transcription text in CallRecordDTO
            callRecordDTO.setRecordingText(transcription);

            // Do NOT save, just return the transcription result
            return ResponseEntity.ok(callRecordDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing transcription: " + e.getMessage());
        }
    }


}
