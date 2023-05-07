package com.ca.security.roles.demo78.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.ca.security.roles.demo78")

public class SecurityConfig {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

   // private DataSource dataSource;

   /* @Autowired
    @Qualifier("userDetailsServiceImpl")
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }*/

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
/*
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
*/

   /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers("/users/**", "/", "/list/**").hasAuthority("ADMIN")
             .and()
                .authorizeHttpRequests()
                .requestMatchers("/signup/**", "/css/**", "/js/**").permitAll()
                .anyRequest()
                .authenticated()
             .and()
                .formLogin()
                .defaultSuccessUrl("/")
                .loginPage("/login").permitAll()
             .and()
                .rememberMe()
                .userDetailsService(userDetailsService)
                .rememberMeParameter("remember-me")
                .tokenRepository(tokenRepository())
             .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
             .and()
                .build();
    }*/
/*
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }*/

    @Autowired
    private AuthProvider customIdentityAuthenticationProvider;
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
               // .userDetailsService(userDetailsService)
               // .passwordEncoder(passwordEncoder)
                .authenticationProvider(customIdentityAuthenticationProvider);
    }


}
