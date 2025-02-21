package com.project1.call_management_app.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionDTO {
    private UUID sessionId;
    private Long userId;
    private LocalDateTime loginTime;
    private LocalDateTime logoutTime;
    private boolean isActive;
}

