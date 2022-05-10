package ru.volkovd.simpleapp.services;

import org.springframework.stereotype.Service;
import ru.volkovd.simpleapp.models.Post;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService2 {

    private List<Post> posts = new ArrayList<Post>();

    public PostService2(PostService postService) {
        posts.add(new Post(1, "qwe qwe", "asd asd asd"));
        posts.add(new Post(2, "Второй пост", "Описание второго поста"));
        posts.add(new Post(3, "Третий пост", "Описание Третьего поста"));
    }

    public Post save(Post newPost) {

        Integer counter = posts.size();
        Post post = new Post(counter, newPost.getTitle(), newPost.getDescription());
        posts.add(post);
        return post;
    }

}
