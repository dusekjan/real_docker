package com.example.springjpaweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration  // protoze obsahuje Bean  funguje to i u @SpringApplicationSpringBoot
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // zabezpeceni ne na URL ale method
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //aby fungoval ten preauthorize
        http.cors().and()  //aby to povolilo Credentials cors
                // ...
                .authorizeHttpRequests(authorize -> authorize
   //                     .mvcMatchers("/resources/**", "/signup", "/about").permitAll()
                        .mvcMatchers("/students").hasRole("USER")
    //                    .mvcMatchers("/**").hasRole("ADMIN")
    //                    .mvcMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
    //                    .anyRequest().denyAll()
                        .anyRequest().hasRole("ADMIN")
                );
        http.httpBasic(Customizer.withDefaults()); // zvolen druh autentizace
    }

    @Bean
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
