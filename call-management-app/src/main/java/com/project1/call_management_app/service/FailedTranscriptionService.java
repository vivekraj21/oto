package com.project1.call_management_app.service;

import com.project1.call_management_app.model.FailedTranscription;

import java.util.List;

public interface FailedTranscriptionService {

    void saveFailedTranscription(FailedTranscription failedTranscription);

    List<FailedTranscription> getFailedTranscriptionsByUser(Long userId);
}
