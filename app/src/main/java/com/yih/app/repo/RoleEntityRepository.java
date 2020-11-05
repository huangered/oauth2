package com.yih.resource.repo;

import com.yih.resource.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findByUserId(Long userId);
}
