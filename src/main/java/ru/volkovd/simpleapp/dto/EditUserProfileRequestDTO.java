package ru.volkovd.simpleapp.dto;

import lombok.Data;

@Data
public class EditUserProfileRequestDTO {
    private String firstname;
    private String lastname;
}
