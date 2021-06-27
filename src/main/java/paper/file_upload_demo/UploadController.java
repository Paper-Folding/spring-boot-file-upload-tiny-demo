package paper.file_upload_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import paper.file_upload_demo.storage.StorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class UploadController {

    private final StorageService storageService;

    @Autowired
    public UploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping("/")
    public String welcome(Model model) {
        //运用反射，提取全部已上传文件
        model.addAttribute("files",
                storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(UploadController.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                        .collect(Collectors.toList()));
        // 运用反射，提取某个已上传文件
        //model.addAttribute("oneOfFiles", MvcUriComponentsBuilder.fromMethodName(UploadController.class, "serveFile", storageService.loadAsResource("TIM图片20180813195830.jpg").getFilename().toString()).build().toUri().toString());
        return "main";
    }

    String getFileServerPath(String fileInServerName) {
        return MvcUriComponentsBuilder.fromMethodName(UploadController.class, "serveFile", storageService.loadAsResource(fileInServerName).getFilename().toString()).build().toUri().toString();
    }

    @PostMapping("/upload")
    public String upload(MultipartFile uploads, @RequestParam String yeah) {
        String newName = UUID.randomUUID().toString() + uploads.getOriginalFilename().substring(uploads.getOriginalFilename().lastIndexOf("."));
        storageService.storeWithSpecifiedFileName(uploads, newName);
        System.out.println(yeah);
        return "redirect:/";
    }

    @PostMapping("/uploadByAjax")
    @ResponseBody
    public String uploadByAjax(MultipartFile uploads, @RequestParam String yeah) {
        String newName = UUID.randomUUID().toString() + uploads.getOriginalFilename().substring(uploads.getOriginalFilename().lastIndexOf("."));
        storageService.storeWithSpecifiedFileName(uploads, newName);
        return "{\"yeah\":\"" + yeah + "\",\"url\":\"" + getFileServerPath(newName)+"\"}";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
