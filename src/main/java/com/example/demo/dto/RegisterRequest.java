// package com.example.demo.dto;

// import com.example.demo.model.Role;

// public class RegisterRequest {
//     private String username;
//     private String password;
//     private String email;
//     private Role role;

//     // No-argument constructor
//     public RegisterRequest() {}

//     // Getters and setters
//     public String getUsername() { 
//         return username; 
//     }

//     public void setUsername(String username) { 
//         this.username = username; 
//     }

//     public String getPassword() { 
//         return password; 
//     }

//     public void setPassword(String password) { 
//         this.password = password; 
//     }

//     public String getEmail() { 
//         return email; 
//     }

//     public void setEmail(String email) { 
//         this.email = email; 
//     }

//     public Role getRole() { 
//         return role; 
//     }

//     public void setRole(Role role) { 
//         this.role = role; 
//     }
// }
package com.example.demo.dto;

import com.example.demo.model.Role;

public class RegisterRequest {

    private String username;
    private String password;
    private String email;
    private Role role;

    public RegisterRequest() {}

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public Role getRole() { return role; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(Role role) { this.role = role; }
}
