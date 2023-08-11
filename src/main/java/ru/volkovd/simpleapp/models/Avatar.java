package ru.volkovd.simpleapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.IOException;

@Entity
@Table(name = "avatars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Avatar {
    @Id
    private Long id;
    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    public Avatar() {
    }

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

    @Override
    public String toString() {
        return this.fileName;
    }
}
