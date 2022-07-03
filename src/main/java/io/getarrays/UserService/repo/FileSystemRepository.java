package io.getarrays.UserService.repo;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class FileSystemRepository {

    String RESOURCES_DIR = FileSystemRepository.class.getResource("/").getPath();

    // Galgan fileni content bilan imageName bilan Directories da path qaytaradi
    public String writeFile(byte[] content, String imageName) throws Exception {
        Path newFile = Paths.get(RESOURCES_DIR + imageName);
        Files.createDirectories(newFile.getParent());

        Files.write(newFile, content);

        return newFile.toAbsolutePath().toString();
    }

    // Galib durgan location boyicha shu FileResource ni qaytaradi
    public FileSystemResource readFile(String location) {
        try {
            return new FileSystemResource(Paths.get(location));
        } catch (Exception e) {
            //TODO Handle access or file not found problems.
            throw new RuntimeException();
        }
    }
}