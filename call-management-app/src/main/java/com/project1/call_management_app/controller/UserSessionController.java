package com.project1.call_management_app.controller;
import com.project1.call_management_app.dto.UserSessionDTO;
import com.project1.call_management_app.model.UserSession;
import com.project1.call_management_app.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/sessions")
public class UserSessionController {

    private final UserSessionService userSessionService;

    @Autowired
    public UserSessionController(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    // Start a new session
    @PostMapping("/start/{userId}")
    public ResponseEntity<UserSessionDTO> startSession(@PathVariable Long userId) {
        UserSession session = userSessionService.createSession(userId);
        return ResponseEntity.ok(convertToDTO(session));
    }

    // Get active session for a user
    @GetMapping("/active/{userId}")
    public ResponseEntity<UserSessionDTO> getActiveSession(@PathVariable Long userId) {
        Optional<UserSession> session = userSessionService.getActiveSessionByUserId(userId);
        return session.map(value -> ResponseEntity.ok(convertToDTO(value)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get session details by session ID
    @GetMapping("/{sessionId}")
    public ResponseEntity<UserSessionDTO> getSessionById(@PathVariable UUID sessionId) {
        Optional<UserSessionDTO> sessionDTO = userSessionService.getSessionById(sessionId);
        return sessionDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // End session
    @PutMapping("/end/{sessionId}")
    public ResponseEntity<String> endSession(@PathVariable UUID sessionId) {
        userSessionService.endSession(sessionId);
        return ResponseEntity.ok("Session ended successfully.");
    }

    // Convert UserSession to DTO
    private UserSessionDTO convertToDTO(UserSession session) {
        return new UserSessionDTO(
                session.getSessionId(),
                session.getUserId(),
                session.getLoginTime(),
                session.getLogoutTime(),
                session.isActive()
        );
    }
}

