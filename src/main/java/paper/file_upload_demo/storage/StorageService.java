package paper.file_upload_demo.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	void storeWithSourceFileName(MultipartFile file);

	void storeWithSpecifiedFileName(MultipartFile file,String SpecifiedDestinationStorageFileName);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}
