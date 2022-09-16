package com.globalsprint.globalsprint1.config.security.security;

import com.globalsprint.globalsprint1.model.Role;
import com.globalsprint.globalsprint1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {

    private String userName;

    private String password;

    private boolean active;

    private Collection<Role> roles;

    private List<GrantedAuthority> authorities;
    public  MyUserDetails(User user){
        this.userName = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.roles = user.getRoles();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        for (Role role:roles) {
            list.add(new SimpleGrantedAuthority(role.getName()));
        }

        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
