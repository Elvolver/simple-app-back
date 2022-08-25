package ru.volkovd.simpleapp.services;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.volkovd.simpleapp.models.Avatar;
import ru.volkovd.simpleapp.repository.AvatarRepository;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;

    public AvatarServiceImpl(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    @Override
    public Avatar storeFile(MultipartFile file) throws Exception {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                //throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                throw new IOException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Avatar dbFile = new Avatar(fileName, file.getContentType(), file.getBytes());
            return avatarRepository.save(dbFile);
        } catch (IOException ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public Avatar getFile(Long fileId) throws Exception {
        return avatarRepository.findById(fileId)
                .orElseThrow(() -> new Exception("File not found with id " + fileId));

    }

    @Override
    public Avatar save(Avatar avatar) throws Exception {
        return avatarRepository.save(avatar);
    }
}
