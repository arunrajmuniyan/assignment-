package com.core.assignment.admin.entity;

import jakarta.persistence.PostLoad;

public class MyEntityListener {

    @PostLoad
    public void postLoad(UserEntity user) {
        // Populate the transient field
//        user.setAdditionalInfo("This is additional information.");
    }
}