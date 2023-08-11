package ru.volkovd.simpleapp.services;

import ru.volkovd.simpleapp.dto.PostResponse;
import ru.volkovd.simpleapp.dto.PostResponseDTO;
import ru.volkovd.simpleapp.models.Post;

public interface PostService {
    Post save(PostResponseDTO post);

    Post save(Post post);

    PostResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir);

    void delete(Post post);
}
