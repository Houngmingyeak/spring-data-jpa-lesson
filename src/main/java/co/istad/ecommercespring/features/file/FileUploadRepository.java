package co.istad.ecommercespring.features.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileUploadRepository extends JpaRepository<FileUpload, Integer> {

    Optional<FileUpload> findByName(String name);

}
