package VNGroupBy.com.vn.Service.Impl;

import VNGroupBy.com.vn.Exception.BadRequestException;
import VNGroupBy.com.vn.Exception.ImageException;
import VNGroupBy.com.vn.Service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public String saveImageToDisk(MultipartFile image) {
        try {
            //Loại bỏ các ký tự không hợp lệ trong file name
            String fileName = StringUtils.cleanPath(image.getOriginalFilename());
            //get() tạo một đường dẫn tuyệt đối và resolve method nối tên của fileName vào đường dẫn tuyệt đối này
            Path path = Paths.get("./uploads").resolve(fileName);
            //Chuyển dữ liệu thành luồng InputStream, sau đó lưu vào đường dẫn với option là ghi đè các file đã tồn tại
            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            return path.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new BadRequestException("Bad Request!");
        }
    }

    @Override
    public void deleteImageInDisk(String pathName) {
        try {
            Path path= Paths.get(pathName);
            Files.delete(path);
        }
        catch (Exception e) {
            throw new BadRequestException("Bad Request!");
        }
    }

    @Override
    public byte[] getImage(String ImagePath) {
        try {
            Path path = Paths.get(ImagePath);
            return Files.readAllBytes(path);
        } catch (Exception e) {
            throw new ImageException("Không thể tải ảnh");
        }
    }
}
