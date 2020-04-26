package io.stream.com.configurations;

import io.stream.com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    public void configure(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.userDetailsService(userService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                //.antMatchers("/**").authenticated()
                //.antMatchers("/api/v1/**").permitAll()
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception { return super.authenticationManagerBean(); }

    @Bean
    public PasswordEncoder getPasswordEncoder() { return NoOpPasswordEncoder.getInstance(); }
}
