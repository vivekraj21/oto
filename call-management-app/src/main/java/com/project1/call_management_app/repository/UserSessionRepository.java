package com.project1.call_management_app.repository;
import com.project1.call_management_app.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserSessionRepository extends JpaRepository<UserSession, UUID> {

    // Find an active session by userId
    Optional<UserSession> findByUserIdAndIsActiveTrue(Long userId);

    // Find a session by authToken (if needed later)
//    Optional<UserSession> findByAuthToken(String authToken);
}

