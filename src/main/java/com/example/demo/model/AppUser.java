// // package com.example.demo.model;

// // import jakarta.persistence.*;
// // import org.springframework.security.core.GrantedAuthority;
// // import org.springframework.security.core.authority.SimpleGrantedAuthority;
// // import org.springframework.security.core.userdetails.UserDetails;

// // import java.util.Collection;
// // import java.util.List;

// // @Entity
// // @Table(name = "app_user")
// // public class AppUser implements UserDetails {

// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id;

// //     @Column(unique = true)
// //     private String username;

// //     @Column(unique = true)
// //     private String email;

// //     private String password;

// //     @Enumerated(EnumType.STRING)
// //     private Role role;

// //     // ✅ REQUIRED no-args constructor (JPA + tests)
// //     public AppUser() {
// //     }

// //     // ✅ SAFE constructor (tests may use)
// //     public AppUser(String username, String email, String password, Role role) {
// //         this.username = username;
// //         this.email = email;
// //         this.password = password;
// //         this.role = role;
// //     }

// //     // ===== UserDetails methods =====
// //     @Override
// //     public Collection<? extends GrantedAuthority> getAuthorities() {
// //         if (role == null) {
// //             return List.of(); // ✅ avoid NullPointerException in tests
// //         }
// //         return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
// //     }

// //     @Override
// //     public boolean isAccountNonExpired() { return true; }

// //     @Override
// //     public boolean isAccountNonLocked() { return true; }

// //     @Override
// //     public boolean isCredentialsNonExpired() { return true; }

// //     @Override
// //     public boolean isEnabled() { return true; }

// //     // ===== Getters & Setters =====
// //     public Long getId() { return id; }
// //     public void setId(Long id) { this.id = id; }

// //     @Override
// //     public String getUsername() { return username; }
// //     public void setUsername(String username) { this.username = username; }

// //     public String getEmail() { return email; }
// //     public void setEmail(String email) { this.email = email; }

// //     @Override
// //     public String getPassword() { return password; }
// //     public void setPassword(String password) { this.password = password; }

// //     public Role getRole() { return role; }
// //     public void setRole(Role role) { this.role = role; }
// // }
// package com.example.demo.model;

// import jakarta.persistence.*;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import java.util.Collection;
// import java.util.List;

// @Entity
// @Table(name = "app_user")
// public class AppUser implements UserDetails {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(unique = true, nullable = false)
//     private String username;

//     @Column(unique = true, nullable = false)
//     private String email;

//     private String password;

//     @Enumerated(EnumType.STRING)
//     private Role role;

//     public AppUser() {}

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
//     }

//     @Override public boolean isAccountNonExpired() { return true; }
//     @Override public boolean isAccountNonLocked() { return true; }
//     @Override public boolean isCredentialsNonExpired() { return true; }
//     @Override public boolean isEnabled() { return true; }

//     @Override public String getUsername() { return username; }
//     @Override public String getPassword() { return password; }

//     public void setUsername(String username) { this.username = username; }
//     public void setEmail(String email) { this.email = email; }
//     public void setPassword(String password) { this.password = password; }
//     public void setRole(Role role) { this.role = role; }
// }

package com.example.demo.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "app_user")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public AppUser() {}

    public AppUser(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) return List.of();
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @Override
    public String getUsername() { return username; }

    @Override
    public String getPassword() { return password; }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(Role role) { this.role = role; }
}

