package project.capstone.studyPal.service.studyPalService.cloudService;

import project.capstone.studyPal.exception.ImageUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface CloudService {
    String upload(MultipartFile image) throws ImageUploadException;
}
