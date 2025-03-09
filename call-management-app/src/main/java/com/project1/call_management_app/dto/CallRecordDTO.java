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
@AllArgsConstructor
public class CallRecordDTO {
    private UUID callId;
    private String callerName;
    private String callerNumber;
    private String receiverNumber;
    private LocalDateTime callStartTime;
    private LocalDateTime callEndTime;
    private String incomingFilePath;
    private String recordingText;
    private Long userId;
}