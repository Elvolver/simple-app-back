package ru.volkovd.simpleapp.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(EnumSet.of(Permission.DEVELOPERS_READ)),
    ADMIN(EnumSet.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthority() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
