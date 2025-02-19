package com.project1.call_management_app.controller;

import com.project1.call_management_app.dto.CallRecordDTO;
import com.project1.call_management_app.service.CallRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/call-records")
public class CallRecordController {

    @Autowired
    private CallRecordService callRecordService;

    @PostMapping
    public ResponseEntity<CallRecordDTO> saveCallRecord(@RequestBody CallRecordDTO callRecordDTO) {
        return ResponseEntity.ok(callRecordService.saveCallRecord(callRecordDTO));
    }

    @GetMapping("/{callId}")
    public ResponseEntity<CallRecordDTO> getCallRecordById(@PathVariable UUID callId) {
        return ResponseEntity.ok(callRecordService.getCallRecordById(callId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CallRecordDTO>> getCallRecordsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(callRecordService.getCallRecordsByUserId(userId));
    }

    @DeleteMapping("/{callId}")
    public ResponseEntity<Void> deleteCallRecord(@PathVariable UUID callId) {
        callRecordService.deleteCallRecord(callId);
        return ResponseEntity.noContent().build();
    }
}

