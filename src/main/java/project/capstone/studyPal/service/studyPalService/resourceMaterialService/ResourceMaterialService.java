package project.capstone.studyPal.service.studyPalService.resourceMaterialService;

import project.capstone.studyPal.data.models.ResourceMaterial;
import project.capstone.studyPal.dto.request.ResourceMaterialRequest;
import project.capstone.studyPal.dto.response.BookItem;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

public interface ResourceMaterialService {

    List<BookItem> searchResourceMaterials(String topicOfInterest) throws IOException, GeneralSecurityException;
    void addResourceMaterial(ResourceMaterialRequest resourceMaterialRequest) throws GeneralSecurityException, IOException;
    void removeResourceMaterial(Long userId, Long resourceMaterialId);
    void removeResourceMaterial(ResourceMaterial resourceMaterial);
    List<ResourceMaterial> viewAllResourceMaterials();
    ResourceMaterial getResourceMaterialById(Long resourceMaterialId);
    Optional<ResourceMaterial> getResourceMaterialByTitle(String title);

}
