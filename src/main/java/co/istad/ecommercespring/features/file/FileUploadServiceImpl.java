package co.istad.ecommercespring.features.file;

import co.istad.ecommercespring.features.file.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService{

//    private String storageLocation;
    //1. upload file to system
    //2. save file information to database
    @Value("${file.storage-location}")
    private String storageLocation;

    @Value("${file.base-uri}")
    private String baseUri;

    @Override
    public FileUploadResponse upload(MultipartFile file) {

        //prepare file information
            //file name
            //generate Date+Time+Minute_Second

        // Prepare file information
        // File name
        String name = UUID.randomUUID().toString();

        // mypro.file.png
        String extension = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        name += "." + extension; // new-unique-filename.extension

        // Create absolute path to store file
        Path path = Paths.get(storageLocation + name);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "File has been failed to upload");
        }

        return FileUploadResponse.builder()
                .name(name)
                .size(file.getSize())
                .mediaType(file.getContentType())
                .uri(baseUri + name)
                .build();
    }

    @Override
    public List<FileUploadResponse> uploadMultiple(MultipartFile[] files) {
        List<FileUploadResponse> responses = new ArrayList<>();

        for (MultipartFile file : files) {

            // Generate unique file name
            String name = UUID.randomUUID().toString();

            String extension = file.getOriginalFilename()
                    .substring(file.getOriginalFilename().lastIndexOf(".") + 1);

            name += "." + extension;

            // Build absolute path
            Path path = Paths.get(storageLocation + name);

            try {
                Files.copy(file.getInputStream(), path);
            } catch (IOException e) {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "File has been failed to upload: " + file.getOriginalFilename()
                );
            }

            responses.add(
                    FileUploadResponse.builder()
                            .name(name)
                            .size(file.getSize())
                            .mediaType(file.getContentType())
                            .uri(baseUri + name)
                            .build()
            );
        }
        return responses;
    }

    @Override
    public void deleteByName(String name) {
        Path path = Paths.get(storageLocation + name);
        if (!Files.exists(path)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "File not found: " + name
            );
        }
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "File has been failed to delete"
            );
        }
    }
}
