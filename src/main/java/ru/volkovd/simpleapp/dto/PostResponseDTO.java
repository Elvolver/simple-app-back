package ru.volkovd.simpleapp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.volkovd.simpleapp.models.Post;

import javax.validation.constraints.NotBlank;

@Data
public class PostResponseDTO {
    private Long id;
    private String title;
    private String description;
    private UserDTO author;

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.author = new UserDTO(post.getAuthor());
    }
}
