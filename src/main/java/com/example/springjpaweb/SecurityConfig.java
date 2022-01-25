package com.example.springjpaweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // zabezpeceni ne na URL ale method
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * mvcMatcheres omezuji pristup na hlavni stranky, uzivatel je vyzvan formularem pro prihlaseni
         *      - ostatni REST dotazy na databazi jsou oznaceny anotaci @PreAuthorize, ktera nezobrazi formular
         *        ale pokud se uzivatel dostal na stranku, predpoklada se, ze ma prislusna prava a tak jenom kontroluje.
         */
        http.csrf().disable();
        http.httpBasic();
        http.cors()
                .and()
                .authorizeHttpRequests(authorize -> authorize
                    .mvcMatchers("/rozcestnik").hasRole("WORKER")
                    .mvcMatchers("/harmonogram").hasRole("WORKER")
                    .mvcMatchers("/editace-lodi").hasRole("BOSS")
                    .mvcMatchers("/editace-zamestnancu").hasRole("BOSS")
                    .anyRequest().permitAll() // "/" "/uvod"
                );

        http
                .formLogin()
                .defaultSuccessUrl("/", true)
                .failureUrl("/error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .clearAuthentication(true);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
