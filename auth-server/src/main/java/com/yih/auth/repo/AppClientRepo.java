package com.yih.auth.repo;

import com.yih.auth.entity.AppClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppClientRepo extends JpaRepository<AppClientEntity, Long> {

    Optional<AppClientEntity> findByClientId(String clientId);
    Optional<AppClientEntity> findByUserIdAndClientName(Long userId, String clientName);
}
