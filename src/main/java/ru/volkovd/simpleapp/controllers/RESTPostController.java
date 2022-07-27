package ru.volkovd.simpleapp.controllers;

import org.springframework.web.bind.annotation.*;
import ru.volkovd.simpleapp.models.Post;
import ru.volkovd.simpleapp.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class RESTPostController {

    private final PostService postService;

    public RESTPostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @PostMapping
    public Post add(@RequestBody Post post) {
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
