package co.istad.ecommercespring.features.file;

import co.istad.ecommercespring.features.file.dto.FileUploadResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {

    FileUploadResponse upload (MultipartFile file);

    List<FileUploadResponse> uploadMultiple (MultipartFile[] files);

    void deleteByName(String name);

}
