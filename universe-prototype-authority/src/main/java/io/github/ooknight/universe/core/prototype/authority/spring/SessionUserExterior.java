package io.github.ooknight.universe.core.prototype.authority.spring;

import io.github.ooknight.universe.core.prototype.authority.UserState;

import com.google.common.collect.Sets;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;

public final class SessionUserExterior implements UserDetails, CredentialsContainer {

    private Long uid;
    private String username;
    private String password;
    private UserState state;
    private Boolean active;

    public SessionUserExterior(Long uid, String username, String password, UserState state, boolean active) {
        Assert.hasText(username, "USERNAME_IS_EMPTY_WHEN_CREATE_SESSION_USER_EXTERIOR");
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.state = state;
        this.active = active;
    }

    public Long getUid() {
        return this.uid;
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
