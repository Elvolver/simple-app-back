package ru.volkovd.simpleapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.volkovd.simpleapp.models.Post;
import ru.volkovd.simpleapp.models.User;
import ru.volkovd.simpleapp.security.UserDetailsServiceImpl;
import ru.volkovd.simpleapp.services.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:3000/")
@Slf4j
public class RESTPostController {

    private final PostService postService;
    private final UserDetailsServiceImpl userService;

    public RESTPostController(PostService postService, UserDetailsServiceImpl userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @PostMapping
    public Post add(@RequestBody Post post, HttpServletRequest request, HttpServletResponse response) {

        String x = request.getUserPrincipal().getName();

        User user = userService.getUserByUserName(x);

        post.setAuthor(user);

        System.out.println(post.toString());
        Post newPost = postService.save(post);
        System.out.println(newPost);
        return newPost;
    }
    //TODO
    @PostMapping("/{id}")
    public Post update(@PathVariable Integer id, @RequestBody Post post) {
        System.out.println(post.toString());
        Post newPost = postService.save(post);
        System.out.println(newPost);
        return newPost;
    }

    @DeleteMapping("/{post}")
    public void delete(@PathVariable Integer post) {
        if (post != 377) {
            postService.deleteById(post);
            System.out.println("Post deleted: " + post.toString());
        }
    }
}
