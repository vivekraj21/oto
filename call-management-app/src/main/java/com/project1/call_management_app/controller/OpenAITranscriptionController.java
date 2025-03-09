package com.project1.call_management_app.controller;
import com.project1.call_management_app.dto.CallRecordDTO;
import com.project1.call_management_app.model.CallRecord;
import com.project1.call_management_app.service.TranscriptionService;
import com.project1.call_management_app.service.CallRecordService;
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
    public ResponseEntity<CallRecordDTO> transcribeAudio(@RequestBody CallRecordDTO callRecordDTO) {
        // Extract file path from CallRecord object
        String filePath = callRecordDTO.getIncomingFilePath();

        // Get transcription from OpenAI
        String transcription = transcriptionService.transcribeAudio(filePath);

        // Set transcription text in CallRecord
        callRecordDTO.setRecordingText(transcription);

        // Save the updated CallRecord to database
        CallRecordDTO savedRecord = callRecordService.saveCallRecord(callRecordDTO);

        // Return response
        return ResponseEntity.ok(savedRecord);
    }
}
