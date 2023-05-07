package com.ca.security.roles.demo78.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProvider implements AuthenticationProvider{

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder pEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails ;
        try {
             userDetails = userDetailsService.loadUserByUsername(username);
        }
        catch (Exception e)
        {
            throw new BadCredentialsException("No user registered with this details");
         }



        if (userDetails != null)
        {
            if(password.equals(userDetails.getPassword()))
            {
                List<GrantedAuthority> authorities = new ArrayList<>();
                //authorities.add(new SimpleGrantedAuthority(((User)(userDetails.).).role));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            }
            else {
                throw new BadCredentialsException("Invalid password");
            }

        }
        else {
            throw new BadCredentialsException("No user registered with this details");
        }
        /*if (employee.size() > 0) {
            if (pEncoder.matches(password, employee.get(0).getPwd())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(employee.get(0).getRole()));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            }else  {
                throw new BadCredentialsException("Invalid password");
            }
        } else {
            throw new BadCredentialsException("No user registered with this details");
        }*/
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}