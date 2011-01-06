package com.blogspot.fuud.java.bugtracker.web;

import com.blogspot.fuud.java.bugtracker.dao.UserDao;
import com.blogspot.fuud.java.bugtracker.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collection;

public class UserDetailsServiceImpl implements UserDetailsService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        final User user = userDao.getUser(username);
        return user != null ? new AdminDetailsImpl(user) : new GuestDetailsImpl(username);
    }

    private static class AdminDetailsImpl extends GuestDetailsImpl implements UserDetails {
        private final User user;

        public AdminDetailsImpl(User user) {
            super(user.getLogin());
            this.user = user;
        }

        @Override
        public Collection<GrantedAuthority> getAuthorities() {
            return Arrays.<GrantedAuthority>asList(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "admin";
                }
            });
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }
    }

    private static class GuestDetailsImpl implements UserDetails {
        private final String username;

        public GuestDetailsImpl(String username) {
            this.username = username;
        }

        @Override
        public Collection<GrantedAuthority> getAuthorities() {
            return Arrays.<GrantedAuthority>asList(new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "IS_AUTHENTICATED_ANONYMOUSLY";
                }
            });
        }

        @Override
        public String getPassword() {
            return "";
        }

        @Override
        public String getUsername() {
            return username;
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
}
