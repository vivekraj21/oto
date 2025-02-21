package com.project1.call_management_app.service.impl;
import com.project1.call_management_app.dto.UserSessionDTO;
import com.project1.call_management_app.model.UserSession;
import com.project1.call_management_app.repository.UserSessionRepository;
import com.project1.call_management_app.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserSessionServiceImpl implements UserSessionService {

    private final UserSessionRepository userSessionRepository;

    @Autowired
    public UserSessionServiceImpl(UserSessionRepository userSessionRepository) {
        this.userSessionRepository = userSessionRepository;
    }

    @Override
    public UserSession createSession(Long userId) {
        UserSession session = new UserSession();
        session.setSessionId(UUID.randomUUID());
        session.setUserId(userId);
        session.setLoginTime(LocalDateTime.now());
        session.setActive(true); // Mark session as active
        return userSessionRepository.save(session);
    }

    @Override
    public Optional<UserSession> getActiveSessionByUserId(Long userId) {
        return userSessionRepository.findByUserIdAndIsActiveTrue(userId);
    }

    @Override
    public Optional<UserSessionDTO> getSessionById(UUID sessionId) {
        return userSessionRepository.findById(sessionId)
                .map(this::convertToDTO);
    }

    @Override
    public void endSession(UUID sessionId) {
        userSessionRepository.findById(sessionId).ifPresent(session -> {
            session.setLogoutTime(LocalDateTime.now());
            session.setActive(false); // Mark session as inactive
            userSessionRepository.save(session);
        });
    }

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

