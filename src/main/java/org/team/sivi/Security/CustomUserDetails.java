package org.team.sivi.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

        private final String username;
        private final String nombreCompleto;
        private final String password;
        private final boolean isEnabled;
        private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, String nombreCompleto, String password, boolean isEnabled, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.nombreCompleto = nombreCompleto;
        this.password = password;
        this.isEnabled = isEnabled;
        this.authorities = authorities;
    }

    public String getNombreCompleto() {
            return nombreCompleto;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public boolean isEnabled() {
            return isEnabled;
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
    }

