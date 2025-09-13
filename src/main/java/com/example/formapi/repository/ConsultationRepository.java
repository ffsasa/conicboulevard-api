package com.example.formapi.repository;

import com.example.formapi.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, UUID> {
    Page<Consultation> findAllByIsActiveTrueAndProjectName(String projectName, Pageable pageable);
    Optional<Consultation> findByIdAndIsActiveTrue(UUID id);
}

