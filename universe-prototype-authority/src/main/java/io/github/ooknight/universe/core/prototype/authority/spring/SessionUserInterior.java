package io.github.ooknight.universe.core.prototype.authority.spring;

import io.github.ooknight.universe.core.prototype.authority.UserState;

import com.google.common.collect.Sets;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;

public final class SessionUserInterior implements UserDetails, CredentialsContainer {

    private Long uid;
    private Long rid;
    private Long gid;
    private String username;
    private String password;
    private UserState state;
    private Boolean active;
    private Set<Long> permissions = Sets.newHashSet();
    private Set<Long> resources = Sets.newHashSet();

    public SessionUserInterior(Long uid, Long rid, Long gid, String username, String password, UserState state, boolean active) {
        Assert.hasText(username, "USERNAME_IS_EMPTY_WHEN_CREATE_SESSION_USER_INTERIOR");
        this.uid = uid;
        this.rid = rid;
        this.gid = gid;
        this.username = username;
        this.password = password;
        this.state = state;
        this.active = active;
    }

    public void init(Set<Long> permissions, Set<Long> resources) {
        this.permissions = permissions;
        this.resources = resources;
    }

    public Long getUid() {
        return uid;
    }

    public Long getRid() {
        return rid;
    }

    public Long getGid() {
        return gid;
    }

    public Set<Long> getPermissions() {
        return permissions;
    }

    public Set<Long> getResources() {
        return resources;
    }

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= implements UserDetails */

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Sets.newHashSet();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.state == UserState.UNLOCK;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= implements CredentialsContainer */

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
