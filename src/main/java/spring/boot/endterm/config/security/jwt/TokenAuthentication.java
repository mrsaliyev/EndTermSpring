package spring.boot.endterm.config.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.boot.endterm.entity.User;
import spring.boot.endterm.service.userDetails.UserDetailsImpl;

import java.util.Collection;


public class TokenAuthentication implements Authentication {
    private String token;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean isAuthenticated;
    private UserDetails principal;

    public TokenAuthentication(String token) {
        this.token = token;
    }

    public TokenAuthentication(String token, Collection<? extends GrantedAuthority> authorities, boolean isAuthenticated,
                               UserDetails principal) {
        this.token = token;
        this.authorities = authorities;
        this.isAuthenticated = isAuthenticated;
        this.principal = principal;
    }

    public TokenAuthentication(String token, boolean isAuthenticated,
                               UserDetails principal) {
        this.token = token;
        this.authorities = principal.getAuthorities();
        this.isAuthenticated = isAuthenticated;
        this.principal = principal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public String getName() {
        if (principal != null)
            return ((UserDetails) principal).getUsername();
        else
            return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean value) throws IllegalArgumentException {
        isAuthenticated = value;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) this.principal;
        return userDetails.getUser();
    }

}
