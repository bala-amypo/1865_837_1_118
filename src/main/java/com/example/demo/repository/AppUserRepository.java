package com.example.demo.repository;

import com.example.demo.model.*;
import java.util.*;

public interface AppUserRepository {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    AppUser save(AppUser user);
}
