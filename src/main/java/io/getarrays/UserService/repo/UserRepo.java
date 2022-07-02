package io.getarrays.UserService.repo;

import io.getarrays.UserService.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
