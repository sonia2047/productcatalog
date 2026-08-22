package com.productcatalog.productcatalog.repository;

import com.productcatalog.productcatalog.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByTaskPerform(String taskPerform);
}