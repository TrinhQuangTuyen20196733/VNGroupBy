package VNGroupBy.com.vn.Service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    String saveImageToDisk(MultipartFile image);
    void deleteImageInDisk(String pathName);
    byte[] getImage(String ImagePath);
}
