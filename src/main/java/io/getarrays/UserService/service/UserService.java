package io.getarrays.UserService.service;

import io.getarrays.UserService.entities.AppRole;
import io.getarrays.UserService.entities.AppUser;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    AppRole saveRole(AppRole role);
    void addRoleToUser(String username, String roleName);

    AppUser setUserImage(long userId, UserImageDTO imageDTO);

    AppUser getUser(String username);
    List<AppUser> getUsers();
}
