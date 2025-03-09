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
@NoArgsConstructor
@AllArgsConstructor
public class CallRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID callId;
    private String callerName;
    private String callerNumber;
    private String receiverNumber;
    private LocalDateTime callStartTime;
    private LocalDateTime callEndTime;
    private String incomingFilePath;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String recordingText;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}

