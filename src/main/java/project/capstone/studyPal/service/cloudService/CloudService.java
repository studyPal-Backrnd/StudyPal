package project.capstone.studyPal.service.cloudService;

import project.capstone.studyPal.exception.ImageUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface CloudService {
    String upload(MultipartFile image) throws ImageUploadException;
}
