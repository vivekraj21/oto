package com.project1.call_management_app.dto;
import com.project1.call_management_app.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CallRecordDTO {
    private UUID callId;
    private String callerName;
    private String callerNumber;

    public UUID getCallId() {
        return callId;
    }

    public void setCallId(UUID callId) {
        this.callId = callId;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public String getCallerNumber() {
        return callerNumber;
    }

    public void setCallerNumber(String callerNumber) {
        this.callerNumber = callerNumber;
    }

    public String getReceiverNumber() {
        return receiverNumber;
    }

    public void setReceiverNumber(String receiverNumber) {
        this.receiverNumber = receiverNumber;
    }

    public LocalDateTime getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(LocalDateTime callStartTime) {
        this.callStartTime = callStartTime;
    }

    public LocalDateTime getCallEndTime() {
        return callEndTime;
    }

    public void setCallEndTime(LocalDateTime callEndTime) {
        this.callEndTime = callEndTime;
    }

    public String getRecordingText() {
        return recordingText;
    }

    public void setRecordingText(String recordingText) {
        this.recordingText = recordingText;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CallRecordDTO(UUID callId, String callerName, String callerNumber, String receiverNumber, LocalDateTime callStartTime, LocalDateTime callEndTime, String recordingText, Long userId) {
        this.callId = callId;
        this.callerName = callerName;
        this.callerNumber = callerNumber;
        this.receiverNumber = receiverNumber;
        this.callStartTime = callStartTime;
        this.callEndTime = callEndTime;
        this.recordingText = recordingText;
        this.userId = userId;
    }

    private String receiverNumber;
    private LocalDateTime callStartTime;
    private LocalDateTime callEndTime;
    private String recordingText;
    private Long userId;
}