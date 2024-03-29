package ru.volkovd.simpleapp.dto;

import lombok.Data;
import ru.volkovd.simpleapp.models.Post;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private UserDTO author;

    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.author = new UserDTO(post.getAuthor());
    }
}