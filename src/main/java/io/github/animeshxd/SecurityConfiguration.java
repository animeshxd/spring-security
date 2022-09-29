package io.github.animeshxd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import io.github.animeshxd.model.CustomUser;

@Configuration
public class SecurityConfiguration {

    @Autowired
    public CustomUserDetailsService userDetailsService;
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        var user = new CustomUser("admin");
        user.setPassword(passwordEncoder().encode("admin"));
        user.setAuthorities("ROLE_ADMIN");
        user.setActive(true);
        
        userDetailsService.createUser(user);

        user = new CustomUser("user");
        user.setPassword(passwordEncoder().encode("user"));
        user.setAuthorities("ROLE_USER");
        user.setActive(true);

        userDetailsService.createUser(user);

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests((authz) -> authz
            .antMatchers("/").permitAll()
            .antMatchers("/admin").hasRole("ADMIN")
            .antMatchers("/user").hasAnyRole("USER", "ADMIN")
        ).formLogin();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
