// package com.example.demo.security;

// import com.example.demo.model.AppUser;
// import com.example.demo.repository.AppUserRepository;
// import lombok.RequiredArgsConstructor;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// @RequiredArgsConstructor
// public class CustomUserDetailsService implements UserDetailsService {

//     private final AppUserRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         // Test 45 implies login by username
//         AppUser user = userRepository.findByUsername(username)
//                 .or(() -> userRepository.findByEmail(username)) // Fallback to email if needed
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));

//         return User.builder()
//                 .username(user.getUsername())
//                 .password(user.getPassword())
//                 .roles(user.getRole().toString())
//                 .build();
//     }
// }