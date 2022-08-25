package ru.volkovd.simpleapp.services;

import org.springframework.web.multipart.MultipartFile;
import ru.volkovd.simpleapp.models.Avatar;

public interface AvatarService {
    public Avatar storeFile(MultipartFile file) throws Exception;
    public Avatar getFile(Long fileId) throws Exception;
    public Avatar save(Avatar avatar) throws Exception;
}
