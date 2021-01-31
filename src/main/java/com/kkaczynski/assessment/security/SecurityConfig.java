package com.kkaczynski.assessment.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${users.user.password}")
    private String userPassword;

    @Value("${users.admin.password}")
    private String adminPassword;

    @Value("${users.hr.password}")
    private String hrPassword;

    /**
     * @return basic 3 users USER ,ADMIN ,HR
     */
    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails userDetails = User.withUsername("user")
                .password(encoder.encode(userPassword))
                .roles("USER")
                .build();
        UserDetails adminDetails = User.withUsername("admin")
                .password(encoder.encode(adminPassword))
                .roles("ADMIN")
                .build();
        UserDetails hrDetails = User.withUsername("hr")
                .password(encoder.encode(hrPassword))
                .roles("HR")
                .build();
        return new InMemoryUserDetailsManager(userDetails, adminDetails, hrDetails);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/employee/get").permitAll()
                .antMatchers(HttpMethod.DELETE, "/employee/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/employee/update").hasRole("HR")
                .antMatchers(HttpMethod.POST, "/employee/create").hasRole("HR")
                .antMatchers(HttpMethod.GET, "/h2").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();//disable of csrf for postman testing purposes
    }
}
