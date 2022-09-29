package io.github.animeshxd.model;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

@Entity
public class CustomUser {

    @Id
    private String username;
    private String password;
    private boolean active;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorities;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public void setAuthorities(String ...authorities) {
        this.authorities = List.of(authorities);
    }

    public CustomUser(String username, String password, boolean active, List<String> authorities) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.authorities = authorities;
    }

    public CustomUser() {
    }

    public CustomUser(UserDetails user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.active = user.isEnabled();
        this.authorities = user.getAuthorities().stream().map((t) -> t.getAuthority()).toList();
    }

    public CustomUser(String username) {
        this.username = username;
    }
     

    
    
    

}
