package com.ca.security.roles.demo78;

import com.ca.security.roles.demo78.persist.repositories.UserRepository;
import com.ca.security.roles.demo78.security.UserDetailsServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class demo78Application implements CommandLineRunner {

    private final UserRepository userRepository;

    public demo78Application(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(demo78Application.class, args);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(new UserDetailsServiceImpl());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService inMemoryUserDetailsService() {
        UserDetails min = User
            .withUsername("mmam")
            .password(passwordEncoder().encode("mmam"))
            .roles("USER")
            .build();

        UserDetails sup = User
            .withUsername("zzyz")
            .password(passwordEncoder().encode("zzyz"))
            .roles("USER", "ADMIN")
            .build();

        return new InMemoryUserDetailsManager(min, sup);
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            Connection connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3307/goods", "zylius", "seniukas");

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
