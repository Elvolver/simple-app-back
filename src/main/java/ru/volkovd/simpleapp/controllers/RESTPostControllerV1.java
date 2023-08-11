package ru.volkovd.simpleapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.volkovd.simpleapp.dto.PostDTO;
import ru.volkovd.simpleapp.dto.PostResponse;
import ru.volkovd.simpleapp.dto.PostResponseDTO;
import ru.volkovd.simpleapp.models.Post;
import ru.volkovd.simpleapp.models.User;
import ru.volkovd.simpleapp.security.UserDetailsServiceImpl;
import ru.volkovd.simpleapp.services.PostService;
import ru.volkovd.simpleapp.services.PostServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@Slf4j
public class RESTPostControllerV1 {

    private final PostServiceImpl postService;
    private final UserDetailsServiceImpl userService;

    public RESTPostControllerV1(PostServiceImpl postService, UserDetailsServiceImpl userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    @Transactional
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = "${pagination.DEFAULT_PAGE_NUMBER}", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "${pagination.DEFAULT_PAGE_SIZE}", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "${pagination.DEFAULT_SORT_BY}", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "${pagination.DEFAULT_SORT_DIRECTION}", required = false) String sortDir
    ){
        return postService.getAll(pageNo, pageSize, sortBy, sortDir);
    }

    @PostMapping
    @Transactional
    public PostDTO add(@RequestBody Post post, HttpServletRequest request) {
        User user = userService.getUser(request);
        post.setAuthor(user);
        log.info("Adding new post: {}", post);
        return new PostDTO(postService.save(post));
    }

    @DeleteMapping("/{post}")
    public void delete(@PathVariable Post post) {
        if (post.getId() != 377) {
            postService.delete(post);
            log.info("Post deleted: {}", post);
        }
    }

//    TODO
//    @PostMapping("/{id}")
//    public Post update(@PathVariable Integer id, @RequestBody Post post) {
//        System.out.println(post.toString());
//        Post newPost = postService.save(post);
//        System.out.println(newPost);
//        return newPost;
//    }

}
