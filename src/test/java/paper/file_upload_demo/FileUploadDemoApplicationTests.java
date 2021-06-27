package paper.file_upload_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.http.HttpRequest;

@SpringBootTest
class FileUploadDemoApplicationTests {

    @Test
    void TestUploadPath() {
        System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
    }

}
