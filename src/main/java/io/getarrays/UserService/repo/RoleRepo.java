package io.getarrays.UserService.repo;

import io.getarrays.UserService.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<AppRole, Long> {
    AppRole findByName(String name);
}
