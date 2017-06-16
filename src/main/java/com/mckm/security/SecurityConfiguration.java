package com.mckm.security;

import com.mckm.security.filters.SignUpFilter;
import com.mckm.security.providers.SignUpAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by @author km2
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Autowired
    private SignUpAuthProvider signUpAuthProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //for h2 console
                .headers().frameOptions().disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable().cors()
                .and()
                .authorizeRequests()
                .antMatchers("/console").permitAll()
                .antMatchers(EntryPointURLs.LOGIN_ENTRY_POINT).permitAll()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/hello").authenticated();
                .and()
                .addFilterBefore(prepareSignUpFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private AbstractAuthenticationProcessingFilter prepareSignUpFilter() {
        SignUpFilter signUpFilter = new SignUpFilter("/mleko");
        signUpFilter.setAuthenticationManager(this.authenticationManager);
        return signUpFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(signUpAuthProvider);
    }
}
