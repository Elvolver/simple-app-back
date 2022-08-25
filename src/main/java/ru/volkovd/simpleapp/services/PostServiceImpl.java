package ru.volkovd.simpleapp.services;

import org.springframework.stereotype.Service;
import ru.volkovd.simpleapp.dto.PostDTO;
import ru.volkovd.simpleapp.models.Post;
import ru.volkovd.simpleapp.repository.PostRepository;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post save(PostDTO post) {

        return postRepository.save(new Post(post));
    }

    @Override
    public Post save(Post post) {

        return postRepository.save(post);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }
}
