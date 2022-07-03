package io.getarrays.UserService.repo;

import io.getarrays.UserService.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByImageName(String name);
}
