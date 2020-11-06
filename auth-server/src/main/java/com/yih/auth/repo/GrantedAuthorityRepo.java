package com.yih.auth.repo;

import com.yih.auth.entity.AppGrantedAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrantedAuthorityRepo extends JpaRepository<AppGrantedAuthorityEntity, Long> {
    List<AppGrantedAuthorityEntity> findByUserId(Long userId);
}
