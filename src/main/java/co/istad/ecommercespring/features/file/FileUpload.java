package co.istad.ecommercespring.features.file;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false,length = 255)
     private String extension;

    @Column(length = 255)
    private String caption; //describe the meaning of picture

    private Long size;

    private String mediaType;



}
