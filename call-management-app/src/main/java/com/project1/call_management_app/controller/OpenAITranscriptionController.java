package com.project1.call_management_app.controller;
import com.project1.call_management_app.dto.CallRecordDTO;
import com.project1.call_management_app.model.FailedTranscription;
import com.project1.call_management_app.service.TranscriptionService;
import com.project1.call_management_app.service.CallRecordService;
import com.project1.call_management_app.service.FailedTranscriptionService;
import com.project1.call_management_app.service.impl.FailedTranscriptionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openaitranscription")
public class OpenAITranscriptionController {

    private final TranscriptionService transcriptionService;
    private final CallRecordService callRecordService;
    private final FailedTranscriptionService failedTranscriptionService;

    public OpenAITranscriptionController(TranscriptionService transcriptionService,
                                         CallRecordService callRecordService,
                                         FailedTranscriptionService failedTranscriptionService) {
        this.transcriptionService = transcriptionService;
        this.callRecordService = callRecordService;
        this.failedTranscriptionService = failedTranscriptionService;
    }

    @PostMapping("/audio")
    public ResponseEntity<?> transcribeAudio(@RequestBody CallRecordDTO callRecordDTO) {
        String filePath = callRecordDTO.getIncomingFilePath();
        Long userId = callRecordDTO.getUserId();

        try {
            // Attempt transcription
            String transcription = transcriptionService.transcribeAudio(filePath);

            if (transcription == null || transcription.isEmpty()) {
                FailedTranscription failedTranscription = new FailedTranscription();
                failedTranscription.setUserId(userId);
                failedTranscription.setFilePath(filePath);
                failedTranscription.setErrorMessage("Received empty transcription.");
                failedTranscriptionService.saveFailedTranscription(failedTranscription);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Transcription failed: Received empty transcription.");
            }

            if (transcription.toLowerCase().contains("error")) {
                FailedTranscription failedTranscription = new FailedTranscription();
                failedTranscription.setUserId(userId);
                failedTranscription.setFilePath(filePath);
                failedTranscription.setErrorMessage(transcription);
                failedTranscriptionService.saveFailedTranscription(failedTranscription);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Transcription failed: " + transcription);
            }

            // Set transcription text in CallRecordDTO
            callRecordDTO.setRecordingText(transcription);
            return ResponseEntity.ok(callRecordDTO);

        } catch (Exception e) {
            // Save failed transcription due to system error
            FailedTranscription failedTranscription = new FailedTranscription();
            failedTranscription.setUserId(userId);
            failedTranscription.setFilePath(filePath);
            failedTranscription.setErrorMessage(e.getMessage());
            failedTranscriptionService.saveFailedTranscription(failedTranscription);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing transcription: " + e.getMessage());
        }
    }
}

