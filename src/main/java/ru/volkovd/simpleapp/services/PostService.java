package ru.volkovd.simpleapp.services;

import org.springframework.stereotype.Service;
import ru.volkovd.simpleapp.DTO.PostDTO;
import ru.volkovd.simpleapp.models.Post;

import java.util.List;


public interface PostService {
    public Post save(PostDTO post);
    public Post save(Post post);

    public List<Post> getAll();
    public void deleteById(Integer post);
}
