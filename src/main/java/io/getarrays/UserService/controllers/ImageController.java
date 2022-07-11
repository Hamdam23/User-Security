package io.getarrays.UserService.controllers;

import io.getarrays.UserService.entities.Image;
import io.getarrays.UserService.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/images")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public Image uploadImage(@RequestParam MultipartFile multipartImage) {
        return imageService.uploadImage(multipartImage);
    }

    @GetMapping(value = "/{id}")
    public Image getImageById(@PathVariable long id) {
        return imageService.getImageById(id);
    }

    @GetMapping(value = "/download/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource downloadImageByName(@PathVariable String name) throws Exception {
        return imageService.downloadImageByName(name);
    }
}
