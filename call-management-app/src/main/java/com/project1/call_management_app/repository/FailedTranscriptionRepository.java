package com.project1.call_management_app.repository;

import com.project1.call_management_app.model.FailedTranscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FailedTranscriptionRepository extends JpaRepository<FailedTranscription, UUID> {
    List<FailedTranscription> findByUserId(Long userId);
}
