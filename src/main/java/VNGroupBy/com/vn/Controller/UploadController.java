package VNGroupBy.com.vn.Controller;

import VNGroupBy.com.vn.Service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @PostMapping("/image")
    public  String GetImageUrl(@RequestParam("image") MultipartFile image) {
    return uploadService.saveImageToDisk(image);
    }

}
