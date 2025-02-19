package com.project1.call_management_app.service;

import com.project1.call_management_app.dto.CallRecordDTO;

import java.util.List;
import java.util.UUID;

public interface CallRecordService {
    CallRecordDTO saveCallRecord(CallRecordDTO callRecordDTO);
    List<CallRecordDTO> getCallRecordsByUserId(Long userId);
    CallRecordDTO getCallRecordById(UUID callId);
    void deleteCallRecord(UUID callId);
}

