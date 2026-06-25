package co.istad.ecommercespring.features.file;

import co.istad.ecommercespring.features.file.dto.FileUploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService{

    private final FileUploadRepository fileUploadRepository;
    private final FileUploadMapper fileUploadMapper;

    //    private String storageLocation;
    //1. upload file to system
    //2. save file information to database
    @Value("${file.storage-location}")
    private String storageLocation;

    @Value("${file.base-uri}")
    private String baseUri;

    private FileUploadResponse saveFile(MultipartFile file){
        //prepare file information
        //file name
        //generate Date+Time+Minute_Second

        // Prepare file information
        // File name
        // Prepare file information
        // File name
        String name = UUID.randomUUID().toString();

        // mypro.file.png
        String ext = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        // Create absolute path to store file
        Path path = Paths.get(storageLocation + name + "." + ext);

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "File has been failed to upload");
        }

        // Save information file into db
        FileUpload fileUpload = new FileUpload();
        fileUpload.setName(name);
        fileUpload.setExtension(ext);
        fileUpload.setCaption("ISTAD - Advanced IT Institute in Cambodia");
        fileUpload.setSize(file.getSize());
        fileUpload.setMediaType(file.getContentType());
        fileUploadRepository.save(fileUpload);

        return fileUploadMapper.mapFileUploadToFileUploadResponse(fileUpload);

    }


    @Override
    public FileUploadResponse upload(MultipartFile file) {
        return saveFile(file);
    }

    @Override
    public List<FileUploadResponse> uploadMultiple(List<MultipartFile> files) {
        return files.stream()
                .map(this::saveFile)
                .collect(Collectors.toList());
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

    @Override
    public Page<FileUploadResponse> findAll(int pageNumber, int pageSize) {

        Sort sortById =  Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sortById);

        Page<FileUpload> fileUploadResponse = fileUploadRepository.findAll(pageRequest);

        return fileUploadResponse.map(fileUploadMapper::mapFileUploadToFileUploadResponse);
    }

    @Override
    public FileUploadResponse findByName(String name) {
        return fileUploadRepository.findByName(name)
                .map(fileUploadMapper::mapFileUploadToFileUploadResponse)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "File has been upload"));
    }

}
