package ru.volkovd.simpleapp.DTO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class PostDTO {
    @NotBlank
    @Length(min=1)
    private String title;
    @NotBlank
    @Length(min=1)
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
