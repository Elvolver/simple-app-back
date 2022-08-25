package ru.volkovd.simpleapp.services;

import ru.volkovd.simpleapp.dto.PostDTO;
import ru.volkovd.simpleapp.models.Post;
import java.util.List;

public interface PostService {
    Post save(PostDTO post);
    Post save(Post post);

    List<Post> getAll();
    void delete(Post post);
}
