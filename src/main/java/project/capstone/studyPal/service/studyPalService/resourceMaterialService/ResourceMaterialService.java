package project.capstone.studyPal.service.studyPalService.resourceMaterialService;

import project.capstone.studyPal.data.models.ResourceMaterial;
import project.capstone.studyPal.dto.request.ResourceMaterialRequest;

import java.util.List;
import java.util.Optional;

public interface ResourceMaterialService {
    void addResourceMaterial(ResourceMaterialRequest resourceMaterialRequest);
    void removeResourceMaterial(Long resourceMaterialId);
    void removeResourceMaterial(ResourceMaterial resourceMaterial);
    List<ResourceMaterial> viewAllResourceMaterials();
    Optional<ResourceMaterial> getResourceMaterialById(Long resourceMaterialId);
    Optional<ResourceMaterial> getResourceMaterialByTopic(String title);

}
