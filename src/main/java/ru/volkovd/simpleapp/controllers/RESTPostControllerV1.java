package ru.volkovd.simpleapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.volkovd.simpleapp.models.Post;
import ru.volkovd.simpleapp.models.User;
import ru.volkovd.simpleapp.security.UserDetailsServiceImpl;
import ru.volkovd.simpleapp.services.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/posts")
@Slf4j
public class RESTPostControllerV1 {

    private final PostService postService;
    private final UserDetailsServiceImpl userService;

    public RESTPostControllerV1(PostService postService, UserDetailsServiceImpl userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @PostMapping
    public Post add(@RequestBody Post post, HttpServletRequest request) {
        User user = userService.getUser(request);
        post.setAuthor(user);
        log.info("Adding new post: {}", post);
        return postService.save(post);
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
