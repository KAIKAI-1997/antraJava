package com.HWday19.secure;

import org.springframework.security.core.GrantedAuthority;

public enum userRole implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT;

    public String getAuthority() {
        return name();
    }

}
