package paper.file_upload_demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import paper.file_upload_demo.storage.StorageProperties;
import paper.file_upload_demo.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FileUploadDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileUploadDemoApplication.class, args);
    }
}
