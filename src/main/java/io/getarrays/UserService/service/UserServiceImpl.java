package io.getarrays.UserService.service;

import io.getarrays.UserService.dto.UserImageDTO;
import io.getarrays.UserService.entities.AppRole;
import io.getarrays.UserService.entities.AppUser;
import io.getarrays.UserService.entities.Image;
import io.getarrays.UserService.repo.ImageRepository;
import io.getarrays.UserService.repo.RoleRepo;
import io.getarrays.UserService.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final ImageRepository imageRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User  not found in the database");
        } else {
            log.info("User found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole().getName());
        authorities.add(simpleGrantedAuthority);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving a new User to the database");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        log.info("Saving a new Role to the database");
        return roleRepo.save(role);
    }

    @Override
    @Transactional
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role to the user");
        AppUser user = userRepo.findByUsername(username);
        AppRole role = roleRepo.findByName(roleName);
        user.setRole(role);
    }

    @Override
    @Transactional
    public AppUser setUserImage(long userId, UserImageDTO imageDTO) throws RuntimeException {
        log.info("Adding file to the user");
        AppUser user = userRepo.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        Image image = imageRepository.findById(imageDTO.getImageId()).orElseThrow(
                () -> new RuntimeException("Image not found")
        );
        user.setImage(image);
        return user;
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user");
        return userRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

}
