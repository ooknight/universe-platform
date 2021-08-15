package io.github.ooknight.universe.core.prototype.authority.spring;

import io.github.ooknight.universe.core.prototype.authority.InteriorUserType;
import io.github.ooknight.universe.core.prototype.authority.Scope;
import io.github.ooknight.universe.core.prototype.authority.SessionUser;

import com.google.common.collect.Sets;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;

public final class SessionUserAdapter implements SessionUser, UserDetails, CredentialsContainer {

    private Long uid;
    private Long rid;
    private Long gid;
    private String name;
    private String password;
    private InteriorUserType type;
    private int state;
    private Collection<GrantedAuthority> authorities;
    private Set<Long> permissions = Sets.newHashSet();
    private Set<Long> resources = Sets.newHashSet();
    private Set<String> tags = Sets.newHashSet();
    //private Scope scope = Scope.DUMMY();

    /*
     * state -- | &gt;0:normal | 0:expired | &lt;0:locked
     */
    public SessionUserAdapter(Long uid, Long rid, Long gid, String name, String password, InteriorUserType type, int state) {
        Assert.hasText(name, "NAME_IS_EMPTY_WHEN_CREATE_SESSION_USER");
        this.uid = uid;
        this.rid = rid;
        this.gid = gid;
        this.name = name;
        this.password = password;
        this.type = type;
        this.state = state;
        this.authorities = parseAuthorities();
        //this.scope = scope;
        //this.resources = (resources == null ? Sets.newHashSet() : resources);
    }

    public SessionUserAdapter(Long uid, String name, String password) {
        this(uid, null, null, name, password, null, 1);
    }

    public void init(Set<Long> permissions, Set<Long> resources, Set<String> tags, Scope scope) {
        this.permissions = permissions;
        this.resources = resources;
        this.tags = tags;
        //this.scope = scope;
    }

    private Collection<GrantedAuthority> parseAuthorities() {
        Set<GrantedAuthority> result = Sets.newHashSet(new SimpleGrantedAuthority("ROLE_USER"));
        if (this.type == InteriorUserType.ADMIN) {
            result.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return result;
    }

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= implements SessionUser */

    @Override
    public Long uid() {
        return this.uid;
    }

    @Override
    public Long rid() {
        return this.rid;
    }

    @Override
    public Long gid() {
        return this.gid;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public InteriorUserType type() {
        return this.type;
    }

    @Override
    public Set<Long> permissions() {
        return this.permissions;
    }

    @Override
    public Set<Long> resources() {
        return this.resources;
    }

    @Override
    public Set<String> tags() {
        return this.tags;
    }

    //@Override
    //public Scope scope() {
    //    return this.scope;
    //}

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= implements UserDetails */

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.state >= 1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.state >= 1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.state >= 1;
    }

    @Override
    public boolean isEnabled() {
        return this.state >= 1;
    }

    /* ========= ========= ========= ========= ========= ========= ========= ========= ========= implements CredentialsContainer */

    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
