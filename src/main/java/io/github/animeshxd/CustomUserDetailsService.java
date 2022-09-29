package io.github.animeshxd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.animeshxd.database.UserRepository;
import io.github.animeshxd.model.CustomUser;
import io.github.animeshxd.model.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    public UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findById(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomUserDetails(user.get());
    }

    public void createUser(CustomUser user) {
        repository.save(user);
    }

}