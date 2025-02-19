package com.project1.call_management_app.service.impl;

import com.project1.call_management_app.dto.CallRecordDTO;
import com.project1.call_management_app.model.CallRecord;
import com.project1.call_management_app.model.User;
import com.project1.call_management_app.repository.CallRecordRepository;
import com.project1.call_management_app.repository.UserRepository;
import com.project1.call_management_app.service.CallRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CallRecordServiceImpl implements CallRecordService {

    @Autowired
    private CallRecordRepository callRecordRepository;

    @Autowired
    private UserRepository userRepository;

    private CallRecordDTO convertToDTO(CallRecord callRecord) {
        return new CallRecordDTO(
                callRecord.getCallId(),
                callRecord.getCallerName(),
                callRecord.getCallerNumber(),
                callRecord.getReceiverNumber(),
                callRecord.getCallStartTime(),
                callRecord.getCallEndTime(),
                callRecord.getRecordingText(),
                callRecord.getUser().getId()
        );
    }

    private CallRecord convertToEntity(CallRecordDTO callRecordDTO) {
        Optional<User> user = userRepository.findById(callRecordDTO.getUserId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return new CallRecord(
                null,
                callRecordDTO.getCallerName(),
                callRecordDTO.getCallerNumber(),
                callRecordDTO.getReceiverNumber(),
                callRecordDTO.getCallStartTime(),
                callRecordDTO.getCallEndTime(),
                callRecordDTO.getRecordingText(),
                user.get()
        );
    }

    @Override
    public CallRecordDTO saveCallRecord(CallRecordDTO callRecordDTO) {
        CallRecord callRecord = callRecordRepository.save(convertToEntity(callRecordDTO));
        return convertToDTO(callRecord);
    }

    @Override
    public List<CallRecordDTO> getCallRecordsByUserId(Long userId) {
        return callRecordRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CallRecordDTO getCallRecordById(UUID callId) {
        return callRecordRepository.findById(callId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new IllegalArgumentException("Call Record not found"));
    }

    @Override
    public void deleteCallRecord(UUID callId) {
        callRecordRepository.deleteById(callId);
    }
}

