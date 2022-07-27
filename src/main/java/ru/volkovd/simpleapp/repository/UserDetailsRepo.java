package ru.volkovd.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volkovd.simpleapp.models.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
