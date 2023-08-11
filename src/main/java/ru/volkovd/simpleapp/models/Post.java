package ru.volkovd.simpleapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.volkovd.simpleapp.dto.PostResponseDTO;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter @Setter @RequiredArgsConstructor @EqualsAndHashCode
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER, optional = false)
    private User author;

    public Post(PostResponseDTO post) {
        this.title = post.getTitle();
        this.description = post.getDescription();
    }

    @Override
    public String toString() {
        return this.title + " " + this.description;
    }
}
