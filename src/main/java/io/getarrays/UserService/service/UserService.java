package io.getarrays.UserService.service;

import io.getarrays.UserService.domain.AppRole;
import io.getarrays.UserService.domain.AppUser;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    AppRole saveRole(AppRole role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
}
