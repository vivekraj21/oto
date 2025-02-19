package com.project1.call_management_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Setter
@Getter
@Entity
@Table(name = "call_records")
@Data
@NoArgsConstructor
public class CallRecord {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CallRecord(UUID callId, String callerName, String callerNumber, String receiverNumber, LocalDateTime callStartTime, LocalDateTime callEndTime, String recordingText, User user) {
        this.callId = callId;
        this.callerName = callerName;
        this.callerNumber = callerNumber;
        this.receiverNumber = receiverNumber;
        this.callStartTime = callStartTime;
        this.callEndTime = callEndTime;
        this.recordingText = recordingText;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID callId;
    private String callerName;
    private String callerNumber;
    private String receiverNumber;
    private LocalDateTime callStartTime;
    private LocalDateTime callEndTime;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String recordingText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

