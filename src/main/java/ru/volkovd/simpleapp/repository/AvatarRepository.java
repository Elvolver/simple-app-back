package ru.volkovd.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volkovd.simpleapp.models.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}