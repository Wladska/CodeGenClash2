package com.wladska.masters.experiment2.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, USER;  // Define roles as needed

    @Override
    public String getAuthority() {
        return name();  // Returns the name of the enum as the authority
    }
}
