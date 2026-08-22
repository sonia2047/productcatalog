package com.productcatalog.productcatalog.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    @Column(nullable = false, unique = true)
    private String taskPerform;

    public Permission() {
    }

    public Permission(String taskPerform) {
        this.taskPerform = taskPerform;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public String getTaskPerform() {
        return taskPerform;
    }

    public void setTaskPerform(String taskPerform) {
        this.taskPerform = taskPerform;
    }
}