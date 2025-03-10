package com.project1.call_management_app.service.impl;

import com.project1.call_management_app.model.FailedTranscription;
import com.project1.call_management_app.repository.FailedTranscriptionRepository;
import com.project1.call_management_app.service.FailedTranscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FailedTranscriptionServiceImpl implements FailedTranscriptionService {
    @Autowired
    private FailedTranscriptionRepository failedTranscriptionRepository;

    @Override
    public void saveFailedTranscription(Long userId, String filePath, String errorMessage) {
        FailedTranscription failedTranscription = new FailedTranscription(userId, filePath, errorMessage);
        failedTranscriptionRepository.save(failedTranscription);
    }

    @Override
    public List<FailedTranscription> getFailedTranscriptionsByUser(Long userId) {
        return failedTranscriptionRepository.findByUserId(userId);
    }
}
