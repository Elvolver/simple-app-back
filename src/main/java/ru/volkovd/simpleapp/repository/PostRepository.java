package ru.volkovd.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.volkovd.simpleapp.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
