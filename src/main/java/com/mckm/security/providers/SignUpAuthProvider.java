package com.mckm.security.providers;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by @author km2
 */
@Component
public class SignUpAuthProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();

        System.out.println(login + "|" + password);
        List<GrantedAuthority> grantedAuths = new ArrayList<>();

        return new UsernamePasswordAuthenticationToken(authentication, null,grantedAuths);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        System.out.println("isAssignable|"+(UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass)));
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
