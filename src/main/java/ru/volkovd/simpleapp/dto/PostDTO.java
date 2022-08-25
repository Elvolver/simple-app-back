package ru.volkovd.simpleapp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class PostDTO {
    @NotBlank
    @Length(min=1)
    private String title;
    @NotBlank
    @Length(min=1)
    private String description;
}
