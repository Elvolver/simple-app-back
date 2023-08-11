package ru.volkovd.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volkovd.simpleapp.models.Post;
import ru.volkovd.simpleapp.models.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
