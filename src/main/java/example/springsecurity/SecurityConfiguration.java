package example.springsecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
    

    @Bean 
    public UserDetailsManager userDetailsManager(){

        UserDetails user = 
            User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        
        UserDetails admin = 
            User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        
        var userDetailsManager = new JdbcUserDetailsManager(dataSource());
        userDetailsManager.createUser(user);
        userDetailsManager.createUser(admin);
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers("/user").hasAnyRole("USER", "ADMIN")
            .antMatchers("/admin").hasRole("ADMIN")
            .and()
            .formLogin();

        return http.build();
    }
        
    
}
