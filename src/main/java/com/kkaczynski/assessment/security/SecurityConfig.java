package com.kkaczynski.assessment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * @return basic 3 users USER ,ADMIN ,EDITOR
     */
    @Bean
    public UserDetailsService userDetailsService() {
        //todo
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        UserDetails adminDetails = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        UserDetails hrDetails = User.withDefaultPasswordEncoder()
                .username("hr")
                .password("hr")
                .roles("HR")
                .build();
        return new InMemoryUserDetailsManager(userDetails, adminDetails, hrDetails);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/employee").permitAll()
                .antMatchers(HttpMethod.DELETE, "/employee").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/employee").hasRole("HR")
                .antMatchers(HttpMethod.POST, "/employee").hasRole("HR")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();//disable of csrf for postman testing purposes

    }
}
