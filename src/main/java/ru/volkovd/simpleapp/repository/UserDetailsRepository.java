package ru.volkovd.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volkovd.simpleapp.models.User;
import ru.volkovd.simpleapp.models.UserProfile;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findById(Long id);
}
