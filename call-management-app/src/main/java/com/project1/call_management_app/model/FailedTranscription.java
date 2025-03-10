package com.project1.call_management_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "failed_transcriptions")
@NoArgsConstructor
@AllArgsConstructor
public class FailedTranscription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Long userId;
    private String filePath;
    private String errorMessage;
    private LocalDateTime timestamp;

    public FailedTranscription(Long userId, String filePath, String errorMessage) {
    }
}
