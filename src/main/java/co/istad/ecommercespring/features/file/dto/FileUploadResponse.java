package co.istad.ecommercespring.features.file.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Builder
public record FileUploadResponse(

        String name,
        String extension,
        String caption,
        Long size,
        String mediaType,
        String uri
//        String downloadUri

) {
}
