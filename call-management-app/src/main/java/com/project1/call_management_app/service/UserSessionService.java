package com.project1.call_management_app.service;
import com.project1.call_management_app.dto.UserSessionDTO;
import com.project1.call_management_app.model.UserSession;

import java.util.Optional;
import java.util.UUID;

public interface UserSessionService {

    // Create a new session when a user logs in
    UserSession createSession(Long userId);

    // Retrieve an active session by user ID
    Optional<UserSession> getActiveSessionByUserId(Long userId);

    // Retrieve a session by session ID
    Optional<UserSessionDTO> getSessionById(UUID sessionId);

    // End a session when a user logs out
    void endSession(UUID sessionId);
}

