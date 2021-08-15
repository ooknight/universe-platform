package io.github.ooknight.universe.core.client.web.security.access;

import io.github.ooknight.universe.core.prototype.authority.SessionUser;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public class StandardPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        SessionUser su = (SessionUser) authentication.getPrincipal();
        return su.resources().contains(targetDomainObject.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
