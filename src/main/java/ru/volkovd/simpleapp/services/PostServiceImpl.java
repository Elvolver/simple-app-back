package ru.volkovd.simpleapp.services;

import org.springframework.stereotype.Service;
import ru.volkovd.simpleapp.dto.PostDTO;
import ru.volkovd.simpleapp.dto.PostResponse;
import ru.volkovd.simpleapp.dto.PostResponseDTO;
import ru.volkovd.simpleapp.models.Post;
import ru.volkovd.simpleapp.repository.PostRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post save(PostResponseDTO post) {
        return postRepository.save(new Post(post));
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public PostResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> postsPage = postRepository.findAll(pageable);

        List<Post> listOfPosts = postsPage.getContent();

        List<PostDTO> posts = listOfPosts.stream().map(PostDTO::new).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setPosts(posts);
        postResponse.setPageNo(postsPage.getNumber());
        postResponse.setPageSize(postsPage.getSize());
        postResponse.setTotalElements(postsPage.getTotalElements());
        postResponse.setTotalPages(postsPage.getTotalPages());
        postResponse.setLast(postsPage.isLast());

        return postResponse;
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }
}
