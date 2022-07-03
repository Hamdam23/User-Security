package io.getarrays.UserService.service;

import io.getarrays.UserService.entities.AppUser;
import io.getarrays.UserService.entities.Image;
import io.getarrays.UserService.repo.FileSystemRepository;
import io.getarrays.UserService.repo.ImageRepository;
import lombok.RequiredArgsConstructor;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final FileSystemRepository fileRepository;

    public Image getImageById(long id) {
        return imageRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Image not found")
        );
    }

    public Image uploadImage(MultipartFile file) {
        try {
            // TODO: 03/07/22 implement file extension
            //now: imageName=tree-2022.jpg7842145213457124638
            //must be: imageName=tree-2022-7842145213457124638.jpg
            //can be a problem
            String fileNameWithOutExt = FilenameUtils.removeExtension(file.getOriginalFilename()).replace(" ", "-");
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String name = fileNameWithOutExt + "-" + new Date().getTime() + "." + extension;
            String location = fileRepository.writeFile(file.getBytes(), name);
            return imageRepository.save(new Image(name, location));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Non-valid file uploaded");
        }
    }

//    {
//        "name":"faf",
//         "profile_url":"/1212141241.jpg"
//    }
//    e-mehmon.uz/api/v1/images/1
//    images.proyektnomi.uz/rasmnomi.kengaytmasi

    public FileSystemResource downloadImageByName(String name) throws Exception {
        Image image = imageRepository.findByImageName(name).orElseThrow(
                () -> new RuntimeException("...")
        );
        return fileRepository.readFile(image.getLocation());
    }
}
