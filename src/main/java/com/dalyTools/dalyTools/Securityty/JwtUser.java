package com.dalyTools.dalyTools.Securityty;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class JwtUser implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<GrantedAuthority> authorities;

    public JwtUser(String username, String password,  GrantedAuthority authorities) {
        this.username = username;
        this.password = password;
        this.authorities= new ArrayList<>();
        this.authorities.add(authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
