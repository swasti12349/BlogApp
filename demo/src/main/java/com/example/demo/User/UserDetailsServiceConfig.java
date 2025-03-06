// package com.example.demo.User;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;


// Custom User Authentication

// @Configuration
// public class UserDetailsServiceConfig {

//     @Bean
//     public UserDetailsService userDetailsService() {
//         UserDetails user = User.withDefaultPasswordEncoder()
//             .username("user")
//             .password("password")
//             .roles("USER")
//             .build();
//         UserDetails admin = User.withDefaultPasswordEncoder()
//             .username("admin")
//             .password("admin")
//             .roles("ADMIN")
//             .build();
//         return new InMemoryUserDetailsManager(user, admin);
//     }
// }
