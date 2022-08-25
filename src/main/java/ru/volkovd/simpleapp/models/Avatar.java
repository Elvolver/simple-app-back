package ru.volkovd.simpleapp.models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name = "avatars")
public class Avatar {
    @Id
    private Long id;
    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    public Avatar() {}

    public Avatar(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public byte[] getData() {
        return this.data;
    }
    public void setData(MultipartFile file) throws IOException {
        this.fileName = file.getOriginalFilename();
        this.fileType = file.getContentType();
        this.data = file.getBytes();
    }

    public Avatar(MultipartFile file) throws IOException {
        setData(file);
    }
}
